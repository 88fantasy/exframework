package org.exframework.support.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class DefaultContextResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		MediaType type = responseContext.getMediaType();
        if (type != null) {
            if (  (type.equals(MediaType.APPLICATION_JSON_TYPE) 
            		|| type.equals(MediaType.APPLICATION_XML_TYPE)
            		||type.equals(MediaType.TEXT_HTML_TYPE)
            		||type.equals(MediaType.TEXT_PLAIN_TYPE)
            		||type.equals(MediaType.TEXT_XML_TYPE)) 
            		&& !type.getParameters().containsKey(MediaType.CHARSET_PARAMETER)) {
                MediaType typeWithCharset = type.withCharset("utf-8");
                responseContext.getHeaders().putSingle("Content-Type", typeWithCharset);
            }
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        }

	}

}
