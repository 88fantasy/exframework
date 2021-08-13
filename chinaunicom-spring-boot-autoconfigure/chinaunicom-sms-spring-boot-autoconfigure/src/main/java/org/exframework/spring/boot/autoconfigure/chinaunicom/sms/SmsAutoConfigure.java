package org.exframework.spring.boot.autoconfigure.chinaunicom.sms;

import org.exframework.support.common.util.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author rwe Date: 2021年5月20日
 * <p>
 * Copyright @ 2021
 */
@Configuration
@EnableConfigurationProperties(value = SmsProperties.class)
public class SmsAutoConfigure {

    private Logger log = LoggerFactory.getLogger(SmsAutoConfigure.class);

    @Autowired
    SmsProperties smsProperties;

    @Autowired
    SmsHttpClient smsHttpClient;


    @Bean
    @Lazy
    @ConditionalOnMissingBean
    public SmsClient smsClient() {
        if (smsProperties != null &&
                StrUtils.hasText(smsProperties.getUrl()) &&
                StrUtils.hasText(smsProperties.getAccountSid()) &&
                StrUtils.hasText(smsProperties.getApi()) &&
                StrUtils.hasText(smsProperties.getAuthToken())
        ) {

            log.warn("如非使用默认配置appId，请在调用时传参");
            return new DefaultSmsClient.Builder()
                    .properties(smsProperties)
                    .client(smsHttpClient)
                    .build();
        } else {
            return new EmptySmsClient();
        }
    }
}
