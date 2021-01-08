package com.gzmpc.core.web.controller;



import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.core.web.constants.WebApiConstants;
import com.gzmpc.support.common.build.BuildService;
import com.gzmpc.support.rest.entity.ApiResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "系统")
public class SystemController {
	
	@Autowired
	BuildService buildService;
			
	@ApiOperation(value = "获取表单属性")
	@RequestMapping(value = WebApiConstants.API_SYS_BUILD, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponse build(@Valid @ApiParam(value = "beanid", required = true)  @NotEmpty @PathVariable String beanid) {
		buildService.build(beanid);
		return new ApiResponse();
	}
	
	@ApiOperation(value = "获取表单属性")
	@RequestMapping(value = WebApiConstants.API_SYS_RELOAD, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponse reload() {
		buildService.reload();
		return new ApiResponse();
	}
}
