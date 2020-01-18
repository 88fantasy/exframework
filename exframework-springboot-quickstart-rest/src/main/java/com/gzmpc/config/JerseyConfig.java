package com.gzmpc.config;


import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gzmpc.support.filter.DefaultContextResponseFilter;


/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
	
	@Value("${swaggerBaseUrl:127.0.0.1:8080}")
	private String baseUrl;

	public JerseyConfig() {

		packages("com.gzmpc.sample.api.rest");
		// packages("com.gzmpc.api.document");

		// error handle
		packages("com.gzmpc.errorhandle");

		register(LoggingFeature.class);
		register(JacksonFeature.class);
		// register(JacksonFeature.class);
		// register(CustomMapperProvider.class);
		register(DefaultContextResponseFilter.class);

		packages("io.swagger.v3.jaxrs2.integration.resources");
	}
	

}