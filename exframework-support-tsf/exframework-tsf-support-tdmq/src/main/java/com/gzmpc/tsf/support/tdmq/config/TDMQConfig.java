package com.gzmpc.tsf.support.tdmq.config;

import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


@Configuration
public class TDMQConfig {
	
	private Log log = LogFactory.getLog(TDMQConfig.class.getName());
	

	@Value("microservice.config.tunnel.url")
	String tunnerUrl;

	@Value("microservice.config.tunnel.netModel")
	String netModel;

	@Value("microservice.config.tunnel.secret")
	String secret;
	
	@Bean
	@ConditionalOnMissingBean(name = "defaultPulsarClient")
	public PulsarClient defaultPulsarClient() {
		log.warn("如非使用默认配置tunnerUrl，请自行配置microservice.config.tunnel.url");
		log.warn("如非使用默认配置netModel，请自行配置microservice.config.tunnel.netModel");
		log.warn("如非使用默认配置secret，请自行配置microservice.config.tunnel.secret");
		if(StringUtils.isEmpty(tunnerUrl)) {
			log.error("tunnerUrl不能为null，配置microservice.config.tunnel.url");
			return null;
		}
		if(StringUtils.isEmpty(netModel)) {
			log.error("netModel不能为null，配置microservice.config.tunnel.netModel");
			return null;
		}
		if(StringUtils.isEmpty(secret)) {
			log.error("secret不能为null，配置microservice.config.tunnel.secret");
			return null;
		}
		

		String serviceUrl = MessageFormat.format("pulsar://{0}/", tunnerUrl);
		String listenerName = MessageFormat.format("custom:{0}", netModel);
		
		try {
			return PulsarClient.builder().serviceUrl(serviceUrl).listenerName(listenerName)
					.authentication(AuthenticationFactory.token(secret)).build();
		} catch (PulsarClientException e) {
			log.error(MessageFormat.format("初始化 TDMQ 失败: {0}", e.getMessage()), e);
		}
		return null;
	}

}
