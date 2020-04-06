package com.gzmpc.quickstart.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
* @author rwe
* @version 创建时间：Jan 31, 2020 7:48:11 PM
* 类说明
*/

@Configuration
public class DbConfig {
	
	@Bean
	@ConfigurationProperties("spring.datasource.druid.system")
	public DataSource systemSource(){
	    return DruidDataSourceBuilder.create().build();
	}
	
}
