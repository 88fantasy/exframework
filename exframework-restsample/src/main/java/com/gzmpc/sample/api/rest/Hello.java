package com.gzmpc.sample.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

/**
* @author rwe
* @version 创建时间：2018年4月19日 上午11:41:13
* 类说明
*/

@Controller
@Path("/hello")
public class Hello {
	
	@GET
	public String hello() {
		return "Hello";
	}

}
