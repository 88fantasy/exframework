package org.exframework.ms.demo.springboot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.tsf.annotation.EnableTsf;


@SpringBootApplication(scanBasePackages = { "org.exframework.*" })
@EnableFeignClients(basePackages = { "org.exframework.*" }) // 使用Feign微服务调用时请启用
@EnableTsf
/**
 * 微服务 启动类
 * 
 * @author rwe
 * @version 创建时间：May 31, 2020 11:29:07 AM
 * 
 */
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}
}