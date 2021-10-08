package org.exframework.portal.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Knife4j 配置 Author: rwe Date: Dec 31, 2020
 *
 * Copyright @ 2020
 * 
 */
@Configuration
@EnableOpenApi
public class AdminKnife4jConfiguration {

	@Bean(value = "adminApi")
	public Docket adminApi() {
		Docket docket = new Docket(DocumentationType.OAS_30)
				.apiInfo(new ApiInfoBuilder().title("Admin Web APIs").description("# Admin web RESTful APIs")
						.termsOfServiceUrl("http://www.xx.com/").contact(new Contact("阮伟儿", "", "ruanweier@gzmpc.com"))
						.version("1.0").build())
				.groupName("Admin Web API").select()
				// 这里指定Controller扫描包路径
				.apis(RequestHandlerSelectors.basePackage("org.exframework.portal.admin.controller"))
				.paths(PathSelectors.any()).build();
		return docket;
	}
}
