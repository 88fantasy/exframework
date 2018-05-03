package com.gzmpc.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

@Controller
@Path("test")
public class TestApi {
	
	@Path("hello")
	@GET
	public Response get()  {
		return Response.ok("hello world!!",MediaType.TEXT_PLAIN_TYPE).build();
	}
	
}
