package org.exframework.spring.boot.pulsar;


import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.ClientBuilder;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.exframework.spring.boot.pulsar.factory.PulsarProducerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Map;


/**
 * canal 自动装配类
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */


public class PulsarConfiguration {
    /**
     * 记录日志
     */
    private final static Logger log = LoggerFactory.getLogger(PulsarConfiguration.class);

    public final static String PREFIX = "pulsar";

    @Bean
    @Lazy
    @ConditionalOnMissingBean
    public PulsarProducerFactory pulsarProducerFactory(PulsarProperties pulsarProperties, ApplicationContext applicationContext, Environment env) {
        PulsarProducerFactory pulsarProducerFactory = new PulsarProducerFactory(applicationContext, env);
        for (Map.Entry<String, PulsarProperties.Broker> entry : pulsarProperties.getBrokers().entrySet()) {
            String name = entry.getKey();
            PulsarProperties.Broker broker = entry.getValue();
            log.warn("如非使用默认配置tunnelUrl，请自行配置pulsar.broker.url");
            log.warn("如非使用默认配置netModel，请自行配置pulsar.broker.netModel");
            log.warn("如非使用默认配置secret，请自行配置pulsar.broker.secret");
            if (!StringUtils.hasText(broker.getUrl())) {
                log.error("tunnelUrl不能为null，配置Pulsar.broker.url");
                continue;
            }


            String serviceUrl = MessageFormat.format("pulsar://{0}/", broker.getUrl());
            String listenerName = MessageFormat.format("custom:{0}", broker.getNetModel());
            ClientBuilder builder = PulsarClient.builder().serviceUrl(serviceUrl);
            if (StringUtils.hasText(broker.getNetModel())) {
                builder = builder.listenerName(listenerName);
            }
            if (StringUtils.hasText(broker.getSecret())) {
                builder = builder.authentication(AuthenticationFactory.token(broker.getSecret()));
            }

            try {
                pulsarProducerFactory.register(name, builder.build());
            } catch (PulsarClientException e) {
                log.error(MessageFormat.format("初始化 Pulsar Broker {0} 失败: {1}", name, e.getMessage()), e);
            }
        }
        return pulsarProducerFactory;
    }
}
