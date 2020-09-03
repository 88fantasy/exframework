package com.gzmpc.support.sso.core.config;

import com.gzmpc.support.sso.core.constant.SecurityConstants;
import com.gzmpc.support.sso.core.service.LoginUserService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yjf
 *
 * @Description://
 */

public class SsoInterceptorHandler implements HandlerInterceptor {

    private String loginUrl;
    private String loginSuccessUrl;
    private String serverUrl;
    private String whiteList;

    private String getUserInfoApi = "/";

    private LoginUserService loginUserService;

    public SsoInterceptorHandler(LoginUserService loginUserService,String loginUrl, String loginSuccessUrl,String serverUrl,String whiteList) {
        this.loginUrl = loginUrl;
        this.loginSuccessUrl = loginSuccessUrl;
        this.serverUrl = serverUrl;
        this.whiteList = whiteList;
        this.loginUserService = loginUserService;

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(SecurityConstants.AUTHORIZATION);
        whiteList = whiteList!=null ? whiteList.trim():"";
        String[] tmpList =  whiteList.split(",");
        AntPathMatcher matcher = new AntPathMatcher();
        for (String str:tmpList){


            if(matcher.match(str, request.getRequestURI())){
                if(loginUserService.getCurrentUser() == null&&authorization!=null){
                    loginUserService.setLoginUserFromServer(authorization,serverUrl);
                }
                return  true;
            }
        }

        if (loginUserService.getCurrentUser() == null&&authorization==null) {
            response.sendRedirect(loginUrl + "?redirect=" + loginSuccessUrl);
            return false;
        }
        else if (loginUserService.getCurrentUser()  == null&&authorization!=null) {
            loginUserService.setLoginUserFromServer(authorization,serverUrl);
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
