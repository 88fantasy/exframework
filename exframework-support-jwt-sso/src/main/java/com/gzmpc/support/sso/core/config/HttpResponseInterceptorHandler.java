package org.exframework.support.sso.core.config;

import org.exframework.support.sso.core.constant.SecurityConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yjf
 * @Date: 2020-5-27 12:42
 * @Description://
 */
public class HttpResponseInterceptorHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader(SecurityConstants.AUTHORIZATION, request.getHeader(SecurityConstants.AUTHORIZATION) );
        response.setHeader("Access-Control-Expose-Headers", SecurityConstants.AUTHORIZATION);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
