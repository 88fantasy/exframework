package org.exframework.alibaba.ms.quickstart.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rwe
 * @date 2022/3/9 17:09
 **/
@Configuration
@EnableOpenApi
public class SampleConfig {


    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder().title("Portal Web APIs").description("# Portal web RESTful APIs")
                        .termsOfServiceUrl("http://www.xx.com/").contact(new Contact("阮伟儿", "", "ruanweier@gzmpc.com"))
                        .version("1.0").build())
                .groupName("Default API").select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("org.exframework.alibaba.ms.quickstart.controller"))
                .paths(PathSelectors.any()).build();
        return docket;
    }

}
