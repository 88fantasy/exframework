package org.exframework.gateway.sso.service;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exframework.gateway.sso.SsoConstants;
import org.exframework.gateway.sso.dto.LoginRequest;
import org.exframework.gateway.sso.dto.BaseLoginResponse;
import org.exframework.gateway.sso.dto.SmsLoginRequest;
import org.exframework.gateway.sso.entity.KeyPairString;
import org.exframework.gateway.sso.entity.UserDetail;
import org.exframework.gateway.sso.exception.NotAuthException;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.rest.exception.ServerException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 单点登录业务逻辑
 *
 * @author rwe
 * @date 2021/11/10 23:42
 **/
public interface ISsoLoginService<T, R extends BaseLoginResponse> {

    String REDIS_KEY_USER_INFO = "/sso/user/%s";

    /**
     * @return redisTemplate
     */
    RedisTemplate<String, Object> getRedisTemplate();

    Class<T> getUserDetailClass();

    /**
     * 获取用户信息
     *
     * @param userId 账号名(或其他)
     * @return 用户信息
     */
    UserDetail<T> getUserDetail(String userId);

    R mapping(BaseLoginResponse response, UserDetail<T> user);

    /**
     * 检验密码
     *
     * @param userDetail 用户信息
     * @param password   密码
     * @return true or false
     */
    boolean checkPassword(UserDetail<T> userDetail, String password);

    /**
     * 缓存时间限制(秒)
     *
     * @param userDetail 用户信息
     * @return 时间限制(秒)
     */
    default long cacheUserInfoTimeout(UserDetail<T> userDetail) {
        return SaManager.getConfig().getTimeout();
    }

    /**
     * 解密密码
     * @param password
     * @return
     */
    default String decrypt(String value, String password) {

        String keyPairString = (String) getRedisTemplate().opsForValue().get(SsoConstants.KEY_SALT_PREFIX.concat(value));
        if (!StrUtils.hasText(keyPairString)) {
            throw new NotAuthException("盐值已失效");
        }

        KeyPairString keyPair;
        try {
            keyPair = new ObjectMapper().readValue(keyPairString, KeyPairString.class);
        } catch (JsonProcessingException e) {
            throw new ServerException("解析盐值失败");
        }
        SM2 sm2 = SmUtil.sm2(keyPair.getPrivateKey(), keyPair.getPublicKey());

        try {
            return StrUtil.utf8Str(sm2.decryptFromBcd(password, KeyType.PrivateKey));
        } catch (Exception e) {
            //此处加密数据可能缺少04开头，解密会失败，需要手动在body前拼上04，body="04"+body
            return StrUtil.utf8Str(sm2.decryptFromBcd("04".concat(password), KeyType.PrivateKey));
        }
    }


    /**
     * 登录逻辑
     *
     * @param request 登录请求
     * @return 登录响应
     */
    default R login(LoginRequest request) throws NotAuthException {

        String username = request.getUsername();

        String password = decrypt(username, request.getPassword());

        UserDetail<T> userDetail = getUserDetail(username);
        try {
            if (!checkPassword(userDetail, password)) {
                throw new NotAuthException();
            }
            R response = login(userDetail, request.getDevice());
            // 登录日志-成功
            saveLoginLog(true, userDetail.getData());
            return response;
        } catch (Exception ex) {
            // 登录日志-失败
            saveLoginLog(false, userDetail.getData());
            throw ex;
        }
    }

    /**
     * 保存登录日志
     *
     * @param pass 是否成功
     * @param t    当前用户信息
     */
    default void saveLoginLog(boolean pass, T t) {

    }

    /**
     * 短信验证码登录
     *
     * @param request 登录请求
     * @return 登录响应
     */
    default BaseLoginResponse smsLogin(SmsLoginRequest request) throws NotAuthException {

        String username = request.getPhone();

        UserDetail<T> userDetail = getUserDetail(username);

        if (!checkSms(request.getPhone(), request.getSms())) {
            throw new NotAuthException("短信验证码错误");
        }

        return login(userDetail, request.getDevice());
    }

    boolean checkSms(String mobile, String sms);

    default R login(UserDetail<T> userDetail, String device) {
        device = StrUtils.hasText(device) ? device : SsoConstants.DEFAULT_DEVICE;
        StpUtil.login(userDetail.getUserId(), device);
        cacheUser(userDetail);
        return mapping(new BaseLoginResponse().setToken(StpUtil.getTokenValueByLoginId(userDetail.getUserId(), device)), userDetail);
    }

    /**
     * 缓存用户信息
     *
     * @param userDetail 用户信息
     */
    default void cacheUser(UserDetail<T> userDetail) {
        String userJson;
        try {
            userJson = new ObjectMapper().writeValueAsString(userDetail.getData());
        } catch (JsonProcessingException e) {
            throw new ServerException(e.getMessage());
        }
        if (StrUtils.hasText(userJson)) {
            getRedisTemplate().opsForValue().set(String.format(REDIS_KEY_USER_INFO, userDetail.getUserId()), userJson, cacheUserInfoTimeout(userDetail), TimeUnit.SECONDS);
        }
    }

    default T currentUserDetail() {
        String user = StpUtil.getLoginIdAsString();
        T t;
        String userJson = (String) getRedisTemplate().opsForValue().get(String.format(ISsoLoginService.REDIS_KEY_USER_INFO, user));
        if (StrUtils.hasText(userJson)) {
            try {
                t = new ObjectMapper().readValue(userJson, getUserDetailClass());
            } catch (JsonProcessingException e) {
                t = getUserDetail(user).getData();
            }
        } else {
            t = getUserDetail(user).getData();
        }
        return t;
    }

    /**
     * 默认登出
     */
    default void logout() {
        StpUtil.logout();
    }
}
