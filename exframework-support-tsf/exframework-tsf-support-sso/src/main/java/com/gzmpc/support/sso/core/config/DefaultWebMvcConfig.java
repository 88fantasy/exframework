package com.gzmpc.support.sso.core.config;


import com.gzmpc.support.sso.core.proxy.LoginUserService;
import com.gzmpc.support.sso.core.resolver.TokenArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 默认SpringMVC拦截器
 *

 * <p>

 */

public class DefaultWebMvcConfig implements WebMvcConfigurer {
	@Lazy
	@Autowired
	private LoginUserService loginUserService;

	@Value ("${spring.userCenter.appSource}")
	String appSpurce;
	/**
	 * Token参数解析
	 *
	 * @param argumentResolvers 解析类
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		//注入用户信息
		argumentResolvers.add(new TokenArgumentResolver(loginUserService,appSpurce));


	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 可添加多个
		registry.addInterceptor(new HttpResponseInterceptorHandler()).addPathPatterns("/**");
	}

}
