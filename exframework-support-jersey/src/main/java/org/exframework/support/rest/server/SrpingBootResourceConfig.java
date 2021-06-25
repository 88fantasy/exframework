package org.exframework.support.rest.server;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

/**
* @author rwe
* @version 创建时间：Jan 21, 2020 11:52:57 AM
* springboot jersey resource config
*/

public class SrpingBootResourceConfig extends ResourceConfig {
	
	/**
	 * springboot 对jersey 的注解
	 * @param packages
	 */
	
	public void springbootJarPackage(String... packages) {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
	    // add more annotation filters if you need
	    scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
	    scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
	    Arrays.stream(packages).forEach( pack -> {
	    	this.registerClasses(scanner.findCandidateComponents(pack).stream().map(beanDefinition -> ClassUtils
		            .resolveClassName(beanDefinition.getBeanClassName(), this.getClassLoader()))
		            .collect(Collectors.toSet()));
	    });
	    
	}
}
