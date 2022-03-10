package org.exframework.springcloud.gateway.quickstart.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author rwe
 */
@SpringBootApplication(scanBasePackages = { "org.exframework.*" })
@EnableFeignClients(basePackages = { "org.exframework.*" }) // 使用Feign微服务调用时请启用
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}