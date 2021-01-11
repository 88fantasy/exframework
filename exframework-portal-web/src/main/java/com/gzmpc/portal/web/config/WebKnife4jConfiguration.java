package com.gzmpc.portal.web.config;

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
 * Knife4j 配置
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Configuration
@EnableOpenApi
public class WebKnife4jConfiguration {
	
	@Bean(value = "exframeApi")
    public Docket exframeApi() {
        Docket docket=new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("Exframe Web APIs")
                        .description("# Exframe web RESTful APIs")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("阮伟儿","","ruanweier@gzmpc.com"))
                        .version("1.0")
                        .build())
                .groupName("Exframe Web API")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.gzmpc.portal.web.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
