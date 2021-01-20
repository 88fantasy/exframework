package com.gzmpc.tsf.support.tdmq.config;

import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.gzmpc.microservice.config.annotation.EnableConfig;
import com.gzmpc.microservice.config.annotation.ParamValue;


@Configuration
@EnableConfig
public class TDMQCloudConfig {
	
	private Log log = LogFactory.getLog(TDMQCloudConfig.class.getName());
	

	@ParamValue("microservice.config.tunnel.url")
	String tunnerUrl;

	@ParamValue("microservice.config.tunnel.netModel")
	String netModel;

	@ParamValue("microservice.config.tunnel.secret")
	String secret;
	
	@Bean("defaultPulsarClient")
	public PulsarClient defaultPulsarClient() {
		if(!StringUtils.hasText(tunnerUrl)) {
			log.error("tunnerUrl不能为null，请检查开发者中心microservice.config.tunnel.url");
			return null;
		}
		if(!StringUtils.hasText(netModel)) {
			log.error("netModel不能为null，请检查开发者中心microservice.config.tunnel.netModel");
			return null;
		}
		if(!StringUtils.hasText(secret)) {
			log.error("secret不能为null，请检查开发者中心microservice.config.tunnel.secret");
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
