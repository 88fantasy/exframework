package org.exframework.backend.quickstart.config;

import org.exframework.support.rest.config.CommonWebMvcConfigure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

/**
 * Knife4j 配置
 * @author rwe
 * @since Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Configuration
public class Knife4jConfiguration extends CommonWebMvcConfigure {

    @Override
    public Predicate<RequestHandler> getSelector() {
        return RequestHandlerSelectors.basePackage("org.exframework.backend.quickstart.controller");
    }
}
