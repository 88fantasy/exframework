package org.exframework.backend.quickstart.config;

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
 * @author rwe
 * @since Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Configuration
@EnableOpenApi
public class Knife4jConfiguration {
	
	@Bean(value = "api")
    public Docket api() {
        Docket docket=new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("APIs")
                        .description("# Web RESTful APIs")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("XXX","","xxx@xxx.com"))
                        .version("1.0")
                        .build())
                .groupName("Web API")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("org.exframework.backend.quickstart.controller"))
                .build();
        return docket;
    }
}
