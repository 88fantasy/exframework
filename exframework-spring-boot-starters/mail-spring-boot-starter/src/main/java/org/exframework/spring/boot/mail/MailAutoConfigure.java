package org.exframework.spring.boot.mail;

import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.internal.MailerRegularBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

/**
 * @author rwe Date: 2021年5月20日
 * <p>
 * Copyright @ 2021
 */
@Configuration
@EnableConfigurationProperties(value = MailProperties.class)
public class MailAutoConfigure {

    private Logger log = LoggerFactory.getLogger(MailAutoConfigure.class);

    @Autowired
    MailProperties mailProperties;

    @Bean
    @Lazy
    @ConditionalOnMissingBean
    public EmailClient emailClient() {
        MailerRegularBuilderImpl builder = MailerBuilder.withSMTPServer(mailProperties.getHost(), mailProperties.getPort(), mailProperties.getUsername(), mailProperties.getPassword());
        if (mailProperties.getProxy() != null) {
            Proxy proxy = mailProperties.getProxy();
            log.info("邮件采用代理发送  host: {}, port: {}, user: {}, password: {}", proxy.getHost(), proxy.getPort(), proxy.getUsername(), proxy.getPassword());
            if (StringUtils.hasText(proxy.getHost())) {
                builder.withProxyHost(proxy.getHost());
            }
            if (proxy.getPort() != null) {
                builder.withProxyPort(proxy.getPort());
            } else {
                builder.withProxyPort(1080);
            }
            if (StringUtils.hasText(proxy.getUsername()) && StringUtils.hasText(proxy.getPassword())) {
                builder.withProxyUsername(proxy.getUsername()).withProxyPassword(proxy.getPassword());
            }
            builder.withProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                    .withProperty("mail.smtp.auth", "true")
                    .withProperty("mail.smtp.timeout", "25000")
                    .withProperty("mail.smtp.ssl.enable", "false");
        }
        Mailer mailer = builder.buildMailer();
        return new DefaultEmailClient(mailProperties.getUsername(), mailer);
    }
}
