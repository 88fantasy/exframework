package org.exframework.rest.druid.webapp.init;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;

import com.gzmpc.support.rest.filter.DefaultContextResponseFilter;

/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

@ApplicationPath("/api/rest")
public class MainApp extends ResourceConfig {

	public MainApp() {

		packages("org.exframework.rest.druid.webapp.sample.api.rest");
		// packages("org.exframework.rest.druid.webapp.api.document");

		// error handle
		packages("org.exframework.rest.druid.webapp.errorhandle");

		register(LoggingFeature.class);
		register(JacksonFeature.class);
		// register(JacksonFeature.class);
		// register(CustomMapperProvider.class);
		register(DefaultContextResponseFilter.class);
		
		packages("io.swagger.v3.jaxrs2.integration.resources");
	}
}