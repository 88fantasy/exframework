#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.init;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;

import com.gzmpc.filter.DefaultContextResponseFilter;

/**
 * @author rwe
 * @version 创建时间：2017年12月14日 下午2:15:12 
 */

@ApplicationPath("/rest")
public class MainApp extends ResourceConfig {
	
	@Value("${symbol_dollar}{swaggerBaseUrl:127.0.0.1:8080}")
	private String baseUrl;

	public MainApp() {

		packages("${package}.sample.api.rest");
		// packages("${package}.api.document");

		// error handle
		packages("${package}.errorhandle");

		register(LoggingFeature.class);
		register(JacksonFeature.class);
		// register(JacksonFeature.class);
		// register(CustomMapperProvider.class);
		register(DefaultContextResponseFilter.class);
		
		packages("io.swagger.v3.jaxrs2.integration.resources");
	}
}