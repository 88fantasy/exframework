package com.gzmpc.init;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.gzmpc.filter.DefaultContextResponseFilter;

@ApplicationPath("/api")
public class MainApp extends ResourceConfig {

	 public MainApp() {

	        packages("com.gzmpc.api");
//	        packages("com.gzmpc.api.document");
	        
	        //error handle
	        packages("com.gzmpc.errorhandle");
	        
	        register(LoggingFeature.class);
	        register(JacksonFeature.class);
	        register(MultiPartFeature.class);
//	        register(JacksonFeature.class);
//	        register(CustomMapperProvider.class);
	        register(DefaultContextResponseFilter.class);
	        
	    }
	 
}
