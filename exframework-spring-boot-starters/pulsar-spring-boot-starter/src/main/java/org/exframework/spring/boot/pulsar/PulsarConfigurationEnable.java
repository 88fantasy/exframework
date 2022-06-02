package org.exframework.spring.boot.pulsar;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 配置启用类
 *
 * @author rwe
 * @date 2022/3/16 09:18
 **/
@Configuration
@ConditionalOnProperty(prefix = PulsarConfiguration.PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = PulsarProperties.class)
public class PulsarConfigurationEnable extends PulsarConfiguration {

}
