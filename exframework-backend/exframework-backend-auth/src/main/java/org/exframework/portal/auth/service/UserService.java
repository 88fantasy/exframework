package org.exframework.portal.auth.service;

import com.usthe.sureness.provider.DefaultAccount;
import com.usthe.sureness.provider.SurenessAccount;
import com.usthe.sureness.util.JsonWebTokenUtil;
import com.usthe.sureness.util.SurenessConstant;
import org.exframework.portal.auth.entity.SysUser;
import org.exframework.portal.auth.vo.LoginResponse;
import org.exframework.support.rest.exception.ApiException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * @author rwe
 * @version 创建时间：2021年4月19日 上午11:31:23
 * 用户服务
 */


public interface UserService {

    long TOKEN_EXPIRED_SECONDS = 86400;


    default SurenessAccount loadSurenessAccount(String account) {
        SysUser user = loadAccount(account);
        return DefaultAccount.builder(user.getUserId())
                .setOwnRoles(permissions(user.getUserId()))
                .setPassword(user.getPassword())
                .setDisabledAccount(!user.getEnabled())
                .setSalt(user.getSalt())
                .build();
    }


    @Transactional(rollbackFor = Exception.class)
    default LoginResponse login(String username, String password) {
        LoginResponse response = new LoginResponse();
        response.setStatus("error");
        SysUser account = loadAccount(username);
        if (account == null || !checkUser(account, password)) {
            throw new ApiException("用户名或密码错误");
        }
        SurenessAccount surenessAccount = loadSurenessAccount(username);
        response.setPermissions(surenessAccount.getOwnRoles());
        String token = token(username, surenessAccount.getOwnRoles(), tokenExpiredSeconds());
        response.setToken(token);
        response.setStatus("ok");
        return response;
    }

    default Long tokenExpiredSeconds() {
        return TOKEN_EXPIRED_SECONDS;
    }

    boolean checkUser(SysUser user, String password);

    @Cacheable(value = "permissionCache", sync = true)
    List<String> permissions(String userId);

    @Cacheable(value = "accountCache", sync = true)
    SysUser loadAccount(String userId);

    default String token(String user, List<String> permissions, Long period) {
        return MessageFormat.format("{0} {1}", SurenessConstant.BEARER, JsonWebTokenUtil.issueJwt(UUID.randomUUID().toString(), user, getIssuer(), period, permissions));
    }

    String getIssuer();
}
