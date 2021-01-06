package com.gzmpc.backend.quickstart.springboot.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

/**
* @author rwe
* @version 创建时间：2018年4月19日 上午11:39:20
* 类说明
*/

@SpringBootApplication(
	scanBasePackages = {
            "com.gzmpc.*", "com.gitee.sunchenbin.mybatis.actable.manager.*"
    },
	exclude = DruidDataSourceAutoConfigure.class
)
@MapperScan(basePackages = {"com.gzmpc.core.mapper.*","com.gitee.sunchenbin.mybatis.actable.dao.*"})
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new Application().configure(new SpringApplicationBuilder(Application.class))
				.run(args);
	}

}
