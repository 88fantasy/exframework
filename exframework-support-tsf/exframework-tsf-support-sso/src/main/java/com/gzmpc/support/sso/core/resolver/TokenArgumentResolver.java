package com.gzmpc.support.sso.core.resolver;


import com.gzmpc.support.sso.core.annotation.LoginUser;
import com.gzmpc.support.sso.core.constant.SecurityConstants;
import com.gzmpc.support.sso.core.dto.LoginUserAccountDto;
import com.gzmpc.support.sso.core.dto.LoginUserDto;
import com.gzmpc.support.sso.core.exception.LoginUserException;
import com.gzmpc.support.sso.core.proxy.UserCenterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Token转化SysUser
 *
 * @author yjf
 * @date
 */

public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private UserCenterService userCenterService;

    private String appSource;

    public TokenArgumentResolver(UserCenterService userCenterService, String appSource) {
        this.userCenterService = userCenterService;
        this.appSource = appSource;

    }


    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) && methodParameter.getParameterType().equals(LoginUserDto.class);
        //return methodParameter.hasParameterAnnotation(LoginUser.class);

    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        boolean isReqLogin = loginUser.isReqLogin();
        boolean isFull = loginUser.isFull();
        boolean isAccount = loginUser.isAccount();

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
        String baseRole = request.getHeader(SecurityConstants.ROLE_HEADER);

        LoginUserDto user = null;
        if (StringUtils.isNotEmpty(userId)) {
            user = new LoginUserDto();
            user.setUid(Integer.valueOf(userId));
            user.setBaseRole(baseRole);

            if (isFull) {
                LoginUserDto infoDto = userCenterService.getLoginUserInfo(userId);
                BeanUtils.copyProperties(infoDto,user);
            }


            if (isAccount) {
                List<LoginUserAccountDto> accountList = userCenterService.getLoginUserAccountByUid(userId);
                if (accountList != null && accountList.size() > 0) {
                    //指定查找相关联系统帐号
                    if (StringUtils.isNotEmpty(appSource)) {
                        accountList = accountList.stream().filter(p -> p.getAppSource().equals(appSource)).collect(Collectors.toList());
                    }
                    user.setLoginUserAccountList(accountList);
                }

            }
        } else if(isReqLogin) {
            throw new LoginUserException("登录帐号为空，请先登录");
        }

        return user;
    }
}
