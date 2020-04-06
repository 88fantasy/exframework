package com.gzmpc.web.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gzmpc.support.common.build.BuildService;

@Controller
@Path("sys")
public class SystemApi {
	
	@Autowired
	BuildService build;
			
	@Path("build")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response build(@QueryParam("beanid") String beanid) {
		if(beanid != null){
			build.build(beanid);
			return Response.ok("success",MediaType.TEXT_PLAIN_TYPE).build();
		}
		else {
			return Response.serverError().build();
		}
	}
	
	@Path("reload")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response reload() {
		build.reload();
		return Response.ok("success",MediaType.TEXT_PLAIN_TYPE).build();
	}
}
