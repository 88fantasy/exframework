package com.gzmpc.portal.jdbc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 * Author: rwe
 * Date: Dec 30, 2020
 *
 * Copyright @ 2020 
 * 
 */

@Configuration
@MapperScan(basePackages = "com.gzmpc.portal.jdbc.mapper")
public class JdbcConfig {

}
