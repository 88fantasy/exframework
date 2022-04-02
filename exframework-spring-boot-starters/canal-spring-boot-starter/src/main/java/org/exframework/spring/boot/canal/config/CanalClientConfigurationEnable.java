package org.exframework.spring.boot.canal.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.exframework.spring.boot.canal.config.CanalClientConfiguration.PREFIX;

/**
 * 配置启用类
 *
 * @author rwe
 * @date 2022/3/16 09:18
 **/
@Configuration
@ConditionalOnProperty(prefix = PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = CanalProperties.class)
public class CanalClientConfigurationEnable extends CanalClientConfiguration {

}
