package com.gzmpc.config;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.stereotype.Component;

import com.gzmpc.support.rest.filter.DefaultContextResponseFilter;
import com.gzmpc.support.rest.server.SrpingBootResourceConfig;


/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends SrpingBootResourceConfig {
	
	public JerseyConfig() {

		springbootJarPackage("com.gzmpc.sample.api.rest");
		// packages("com.gzmpc.api.document");

		// error handle
		springbootJarPackage("com.gzmpc.errorhandle");

		register(LoggingFeature.class);
		register(JacksonFeature.class);
		// register(JacksonFeature.class);
		// register(CustomMapperProvider.class);
		register(DefaultContextResponseFilter.class);

		springbootJarPackage("io.swagger.v3.jaxrs2.integration.resources");
	}
	

}