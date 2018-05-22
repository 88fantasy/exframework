package org.exframework.rest.druid.webapp.sample.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;


/**
* @author rwe
* @version 创建时间：2018年4月19日 上午11:41:13
* swagger 文档详情见 https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations
*/



@Controller
@Path("/hello")
public class Hello {
	
	@GET
	@Operation(summary = "hello World接口", responses = {
		@ApiResponse(responseCode = "200", description = "success") 
	})
	public String hello() {
		return "Hello World";
	}

	@Path("form")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchDataDtl(@FormParam("name") String name) {
		return Response.ok("hello "+name+"!!",MediaType.TEXT_PLAIN_TYPE).build();
	}
}
