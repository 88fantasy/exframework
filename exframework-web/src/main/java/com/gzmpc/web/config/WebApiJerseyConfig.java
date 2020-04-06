package com.gzmpc.web.config;



import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.gzmpc.support.rest.filter.CorsResponseFilter;
import com.gzmpc.support.rest.server.SrpingBootResourceConfig;



/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

public class WebApiJerseyConfig extends SrpingBootResourceConfig {
	
	public WebApiJerseyConfig() {

		springbootJarPackage("com.gzmpc.web.api");

		// error handle
		springbootJarPackage("com.gzmpc.web.errorhandle");

		register(LoggingFeature.class);
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		register(CorsResponseFilter.class);

		springbootJarPackage("io.swagger.v3.jaxrs2.integration.resources");
	}
	

}