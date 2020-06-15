package com.gzmpc.quickstart.config;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.springframework.stereotype.Component;

import com.gzmpc.support.rest.filter.CorsResponseFilter;
import com.gzmpc.support.rest.server.SrpingBootResourceConfig;



/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

@Component
@ApplicationPath("/api/rest")
public class JerseyConfig extends SrpingBootResourceConfig {
	
	public JerseyConfig() {
		super();

		springbootJarPackage("com.gzmpc.quickstart.api.rest");
		// packages("org.exframework.springboot.quickstart.api.document");


		register(LoggingFeature.class);
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		register(CorsResponseFilter.class);
		
		// error handle
		springbootJarPackage("com.gzmpc.quickstart.api.errorhandle");

	}
	

}