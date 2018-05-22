package com.gzmpc.support.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	
	@Path("form")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchDataDtl(@FormParam("name") String name) {
		return Response.ok("hello "+name+"!!",MediaType.TEXT_PLAIN_TYPE).build();
	}
}
