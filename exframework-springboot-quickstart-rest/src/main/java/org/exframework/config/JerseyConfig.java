package org.exframework.config;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.stereotype.Component;

import org.exframework.support.rest.filter.DefaultContextResponseFilter;
import org.exframework.support.rest.server.SrpingBootResourceConfig;


/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends SrpingBootResourceConfig {
	
	public JerseyConfig() {

		springbootJarPackage("org.exframework.sample.api.rest");
		// packages("org.exframework.api.document");

		// error handle
		springbootJarPackage("org.exframework.errorhandle");

		register(LoggingFeature.class);
		register(JacksonFeature.class);
		// register(JacksonFeature.class);
		// register(CustomMapperProvider.class);
		register(DefaultContextResponseFilter.class);

		springbootJarPackage("io.swagger.v3.jaxrs2.integration.resources");
	}
	

}