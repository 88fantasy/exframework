package org.exframework.support.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.exframework.support.common.annotation.ThreadData;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author rwe
 */
@Component
@Aspect
public class ThreadDataAspect {

    @Around("@annotation(threadData)")
    public Object threadData(ProceedingJoinPoint joinPoint, ThreadData threadData) throws Throwable {

        try {
            return joinPoint.proceed();
        } finally {
            ThreadData.ThreadDataClass.clear();
        }
    }

}
