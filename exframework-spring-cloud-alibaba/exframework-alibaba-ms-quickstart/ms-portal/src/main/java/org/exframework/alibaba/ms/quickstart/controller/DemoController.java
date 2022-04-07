package org.exframework.alibaba.ms.quickstart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.alibaba.ms.quickstart.entity.DemoDO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 控制类
 * @author pro
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.PATCH,
		RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE })
@Api(tags = "Demo")
public class DemoController {

	/**
	 * hello world
	 * 
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "hello")
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello(
			@ApiParam(value = "name", required = true) @PathVariable String name) {
		return "Hello "+name;
	}

	@ApiOperation(value = "test")
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DemoDO hello() {
		return new DemoDO().setIdCard("440111198612314514");
	}
}
