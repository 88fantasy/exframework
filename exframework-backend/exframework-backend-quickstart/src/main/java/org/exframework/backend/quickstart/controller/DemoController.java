package org.exframework.backend.quickstart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	 * 获取预售货品目录列表
	 * 
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "获取系统参数配置")
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello(
			@ApiParam(value = "name", required = true) @PathVariable String name) {
		return "Hello "+name;
	}
}
