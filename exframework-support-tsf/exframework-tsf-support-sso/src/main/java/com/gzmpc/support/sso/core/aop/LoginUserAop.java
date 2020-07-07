package com.gzmpc.support.sso.core.aop;

import com.gzmpc.support.sso.core.service.LoginUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class LoginUserAop {
    private static final Logger logger = LoggerFactory.getLogger(LoginUserAop.class);


    @Autowired
    LoginUserService loginUserService;

    @Pointcut("@annotation(com.gzmpc.support.sso.core.annotation.LoginCurrentUser)")
    public void pointCut() {
    }

    /**
     * 前置通知（Before advice）：在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void boBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        //获取参数列表
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        loginUserService.doLogin();

        //else if (isReqLogin) {
        //   throw new LoginUserException("登录帐号为空，请先登录");
        //}

        // logger.info("Method Name : [" + methodName + "] ---> AOP before 参数:[" + args.toString() + "]");
    }


}
