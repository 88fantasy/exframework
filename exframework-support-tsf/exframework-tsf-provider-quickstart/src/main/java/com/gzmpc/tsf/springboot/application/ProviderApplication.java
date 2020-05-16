package com.gzmpc.tsf.springboot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.tsf.annotation.EnableTsf;

@SpringBootApplication(
		scanBasePackages = {
                "com.gzmpc.*"
        }
)
@EnableFeignClients // 使用Feign微服务调用时请启用
@EnableTsf
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}