package org.exframework.spring.boot.pulsar.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;


@Configuration
public class PulsarConfig {
	
	private Log log = LogFactory.getLog(PulsarConfig.class.getName());
	

	@Value("${Pulsar.tunnel.url}")
	String tunnerUrl;

	@Value("${Pulsar.tunnel.netModel}")
	String netModel;

	@Value("${Pulsar.tunnel.secret}")
	String secret;
	
	@Bean
	@Lazy
	@ConditionalOnMissingBean(name = "defaultPulsarClient")
	public PulsarClient defaultPulsarClient() {
		log.warn("如非使用默认配置tunnerUrl，请自行配置Pulsar.tunnel.url");
		log.warn("如非使用默认配置netModel，请自行配置Pulsar.tunnel.netModel");
		log.warn("如非使用默认配置secret，请自行配置Pulsar.tunnel.secret");
		if(!StringUtils.hasText(tunnerUrl)) {
			log.error("tunnerUrl不能为null，配置Pulsar.tunnel.url");
			return null;
		}
		if(!StringUtils.hasText(netModel)) {
			log.error("netModel不能为null，配置Pulsar.tunnel.netModel");
			return null;
		}
		if(!StringUtils.hasText(secret)) {
			log.error("secret不能为null，配置Pulsar.tunnel.secret");
			return null;
		}
		

		String serviceUrl = MessageFormat.format("pulsar://{0}/", tunnerUrl);
		String listenerName = MessageFormat.format("custom:{0}", netModel);
		
		try {
			return PulsarClient.builder().serviceUrl(serviceUrl).listenerName(listenerName)
					.authentication(AuthenticationFactory.token(secret)).build();
		} catch (PulsarClientException e) {
			log.error(MessageFormat.format("初始化 Pulsar 失败: {0}", e.getMessage()), e);
		}
		return null;
	}

}
