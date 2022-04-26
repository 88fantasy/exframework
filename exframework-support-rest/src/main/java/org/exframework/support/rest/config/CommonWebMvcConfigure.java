package org.exframework.support.rest.config;

import org.exframework.support.rest.filter.WebContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

/**
 * 通用 mvc 配置
 *
 * @author rwe
 * @date 2022/4/26 12:57
 **/
public abstract class CommonWebMvcConfigure implements WebMvcConfigurer {

    @Bean
    public WebContextFilter webContextFilter() {
        return new WebContextFilter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * Knife4j api 文档
     *
     * @return
     */
    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .groupName("Apis").select()
                // 这里指定Controller扫描包路径
                .apis(getSelector())
                .paths(PathSelectors.any()).build();
        return docket;
    }

    /**
     * 获取 controller 的断言
     *
     * @return
     */
    public abstract Predicate<RequestHandler> getSelector();
}
