package com.gzmpc.web.errorhandle;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gzmpc.exception.NotAuthorizedException;

@Provider
public class AuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {

	static private Log log = LogFactory.getLog(AuthorizedExceptionMapper.class.getName());
	
	public Response toResponse(NotAuthorizedException ex) {
		
		log.error(ex.getMessage(),ex);
		
		return Response.status(Response.Status.NOT_FOUND)
				.entity(ex.getMessage())
				.type(MediaType.TEXT_PLAIN + ";charset=utf-8") //this has to be set to get the generated JSON 
				.build();
	}
}
