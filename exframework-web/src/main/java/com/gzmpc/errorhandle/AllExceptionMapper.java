package com.gzmpc.errorhandle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Provider
public class AllExceptionMapper implements ExceptionMapper<Exception> {

	static private Log log = LogFactory.getLog(ExceptionMapper.class.getName());
    
    public Response toResponse(Exception exception) {
        
        log.error(exception.getMessage(), exception);
            
        return Response.status(Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN + ";charset=utf-8")
                .build();
    }
}
