package com.gzmpc.support.sso.core.aop;


import com.gzmpc.support.sso.core.service.LoginUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@Component
//@Aspect
public class RequireLoginAop {
    private static final Logger logger = LoggerFactory.getLogger(RequireLoginAop.class);

    @Autowired
    HttpServletResponse response;
    @Autowired
    LoginUserService loginUserService;

    @Value("${jwt.sso.loginUrl}")
    private String loginUrl;

    @Value("${jwt.sso.loginSuccessUrl}")
    private String loginSuccessUrl;


    @Pointcut("@annotation(com.gzmpc.support.sso.core.annotation.RequireLogin)")
    public void pointCut() {
    }

    /**
     * 前置通知（Before advice）：在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void boBefore(JoinPoint joinPoint) {
        if (loginUserService.getCurrentUser() == null) {
            try {
                response.sendRedirect(loginUrl + "?redirect=" + loginSuccessUrl);
            } catch (IOException e) {
                logger.error("跳转登录页面出错-",e);
            }
        }

    }


}
