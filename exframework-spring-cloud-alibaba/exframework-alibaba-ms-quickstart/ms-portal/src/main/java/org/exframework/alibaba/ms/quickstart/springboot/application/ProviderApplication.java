package org.exframework.alibaba.ms.quickstart.springboot.application;

import org.exframework.spring.boot.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"org.exframework.*"})
@EnableFeignClients(basePackages = {"org.exframework.*"})
@EnableCanalClient
/**
 * 微服务 启动类
 *
 * @author rwe
 * @version 创建时间：May 31, 2020 11:29:07 AM
 */
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}