package com.gzmpc.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.gzmpc.metadata.dict.DictionaryScan;

/**
 * 数据库配置
 * Author: rwe
 * Date: Dec 30, 2020
 *
 * Copyright @ 2020 
 * 
 */

@Configuration
@MapperScan(basePackages = "com.gzmpc.core.mapper")
@DictionaryScan("com.gzmpc.metadata.enums")
public class CoreConfig {

}
