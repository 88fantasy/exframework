package org.exframework.spring.boot.captcha.config;

import com.anji.captcha.service.CaptchaCacheService;
import com.anji.captcha.service.impl.CaptchaServiceFactory;
import org.exframework.spring.boot.captcha.properties.CaptchaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 存储策略自动配置.
 *
 */
@Configuration
public class CaptchaStorageAutoConfiguration {

    @Bean(name = "CaptchaCacheService")
    public CaptchaCacheService captchaCacheService(CaptchaProperties CaptchaProperties){
        //缓存类型redis/local/....
        return CaptchaServiceFactory.getCache(CaptchaProperties.getCacheType().name());
    }
}
