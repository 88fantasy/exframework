package com.gzmpc.quickstart.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
* @author rwe
* @version 创建时间：2018年4月19日 上午11:41:13
* 类说明
*/

@Controller
@Path("/hello")
public class Hello {
	
	@GET
	@Operation(summary = "测试",
	tags = {"测试接口"},
	description = "参数结构：{  \"orderno\":\"P00577137\"}",
	responses = {
			@ApiResponse(responseCode = "200", description = "{}"),
			@ApiResponse(responseCode = "500", description = "{\"msg\": \"json参数转换有误\", \"pass\": false}"),
	})
	public String hello() {
		return "Hello";
	}

}
