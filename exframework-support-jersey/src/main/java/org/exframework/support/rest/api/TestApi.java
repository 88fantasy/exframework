package org.exframework.support.rest.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@Controller
//@Path("test")
public class TestApi {
	
	@Path("hello")
	@GET
	public Response get()  {
		return Response.ok("hello world!!",MediaType.TEXT_PLAIN_TYPE).build();
	}
	
	@Path("form")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchDataDtl(@FormParam("name") String name) {
		return Response.ok("hello "+name+"!!",MediaType.TEXT_PLAIN_TYPE).build();
	}
}
