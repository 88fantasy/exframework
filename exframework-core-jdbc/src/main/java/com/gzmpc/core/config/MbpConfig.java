package com.gzmpc.core.config;

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
@MapperScan("com.gzmpc.core.mapper")
public class MbpConfig {

}
