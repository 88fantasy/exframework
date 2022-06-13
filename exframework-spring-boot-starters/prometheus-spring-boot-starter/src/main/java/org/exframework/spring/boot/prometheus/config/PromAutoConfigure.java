package org.exframework.spring.boot.prometheus.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author rwe Date: 2021年5月20日
 *
 * Copyright @ 2021
 * 
 */
@Configuration
public class PromAutoConfigure {

	private Logger log = LoggerFactory.getLogger(PromAutoConfigure.class);


	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(
			@Value("${spring.application.name}") String applicationName,
			@Value("${server.port}") String port) {
		String instance = "unknown";
		try {
			instance = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		String finalInstance = instance;
		return (registry) -> registry.config().commonTags("application", applicationName, "instance", String.format("%s:%s", finalInstance, port));
	}
}
