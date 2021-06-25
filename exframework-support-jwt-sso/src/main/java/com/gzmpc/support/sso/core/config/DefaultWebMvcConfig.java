package org.exframework.support.sso.core.config;


import org.exframework.support.sso.core.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 默认SpringMVC拦截器
 *

 * <p>

 */
//@EnableWebMvc
public class DefaultWebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private LoginUserService loginUserService;

	@Value("${jwt.sso.loginUrl}")
	private String loginUrl;

	@Value("${jwt.sso.loginSuccessUrl}")
	private String loginSuccessUrl;

	@Value("${jwt.sso.serverUrl}")
	private String  serverUrl ;

	@Value("${jwt.sso.whiteList}")
	private String  whiteList ;




	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 可添加多个
		registry.addInterceptor(new HttpResponseInterceptorHandler()).addPathPatterns("/**");
		registry.addInterceptor(new SsoInterceptorHandler(loginUserService,loginUrl,loginSuccessUrl,serverUrl,whiteList)).addPathPatterns("/**");
	}

}
