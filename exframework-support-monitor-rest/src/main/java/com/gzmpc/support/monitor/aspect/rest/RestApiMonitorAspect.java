package com.gzmpc.support.monitor.aspect.rest;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzmpc.support.monitor.stat.rest.RestStat;
import com.gzmpc.support.monitor.stat.rest.RestUriStat;

/**
 * @author rwe
 * @version 创建时间：2018年5月13日 下午1:41:44 rest 接口 监控 切面类
 */

@Aspect
@Component
public class RestApiMonitorAspect {

	@Autowired
	RestStat restStat;

	@Pointcut("@annotation(com.gzmpc.support.monitor.annotation.rest.RestApiMonitor)")
	public void restApiMonitor() {

	}

	// 声明环绕通知
	@Around("restApiMonitor()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

		RestUriStat uristat = null;
		long start = new java.util.Date().getTime();

		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			if (arg instanceof HttpServletRequest) {
				HttpServletRequest req = (HttpServletRequest) arg;
				String uri = req.getRequestURI();
				uristat = restStat.register(uri, new RestUriStat(uri));
			}
		}

		if (uristat != null) {
			uristat.beforeInvoke();
		}

		restStat.beforeInvoke();

		Throwable error = null;
		Object result = null;
		try {
			result = pjp.proceed(args);
		} catch (Throwable e) {
			error = e;
			throw e;
		} finally {
			long end = new java.util.Date().getTime();
			long times = end - start;

			restStat.afterInvoke(error, times);
			
			if (uristat != null) {
				uristat.afterInvoke(error, times);
			}
		}

		return result;
	}
}
