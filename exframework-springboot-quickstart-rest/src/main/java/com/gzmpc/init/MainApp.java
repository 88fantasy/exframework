package com.gzmpc.init;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;

//import com.gzmpc.filter.DefaultContextResponseFilter;

//import io.swagger.jaxrs.config.BeanConfig;

/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

@ApplicationPath("/rest")
public class MainApp extends ResourceConfig {
	
	@Value("${swaggerBaseUrl:127.0.0.1:8080}")
	private String baseUrl;

	public MainApp() {

		packages("com.gzmpc.sample.api.rest");
		// packages("com.gzmpc.api.document");

		// error handle
		packages("com.gzmpc.errorhandle");

		register(LoggingFeature.class);
		register(JacksonFeature.class);
		// register(JacksonFeature.class);
		// register(CustomMapperProvider.class);
//		register(DefaultContextResponseFilter.class);
//		register(io.swagger.jaxrs.listing.ApiListingResource.class);
//		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
//		swaggerConfiguration();
	}

//	private void swaggerConfiguration() {
//		BeanConfig beanConfig = new BeanConfig();
//		beanConfig.setVersion("1.0.0");
//		beanConfig.setSchemes(new String[] { "https" });
//		beanConfig.setHost(baseUrl);
//		beanConfig.setBasePath("sample/api");
//		beanConfig.setResourcePackage("com.gzmpc.sample.api.rest");
//		beanConfig.setScan(true);
//	}

}