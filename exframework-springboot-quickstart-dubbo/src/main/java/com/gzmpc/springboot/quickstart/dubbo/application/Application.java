package com.gzmpc.springboot.quickstart.dubbo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @author rwe
* @version 创建时间：2018年4月19日 上午11:39:20
* springboot starter
*/

@EnableAutoConfiguration
@SpringBootApplication(
		scanBasePackages = {
                "com.gzmpc.*"
        }
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

}
