package com.gzmpc.sample.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gzmpc.support.monitor.annotation.rest.RestApiMonitor;
import com.gzmpc.support.monitor.stat.StatManager;

/**
 * @author rwe
 * @version 创建时间：2018年4月19日 上午11:41:13 类说明
 */

@Controller
@Path("/hello")
public class Hello {

	@RestApiMonitor
	@GET
	public String hello(@Context HttpServletRequest req) {
		System.out.println("hello");
		return "Hello";
	}
	
	@Path("form")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchDataDtl(@FormParam("name") String name) {
		return Response.ok("hello "+name+"!!",MediaType.TEXT_PLAIN_TYPE).build();
	}
	
	@Autowired
	StatManager statManager;
	
	@Path("stat")
	@GET
	public String stat() {
		return statManager.getJSON();
	}
}
