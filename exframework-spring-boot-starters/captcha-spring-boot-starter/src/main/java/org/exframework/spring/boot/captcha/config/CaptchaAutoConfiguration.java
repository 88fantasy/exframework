package org.exframework.spring.boot.captcha.config;

import org.exframework.spring.boot.captcha.properties.CaptchaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
@Import({CaptchaServiceAutoConfiguration.class, CaptchaStorageAutoConfiguration.class})
public class CaptchaAutoConfiguration {
}
