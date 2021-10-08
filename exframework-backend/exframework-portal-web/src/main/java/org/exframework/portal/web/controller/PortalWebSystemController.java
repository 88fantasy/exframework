package org.exframework.portal.web.controller;



import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.support.common.build.BuildService;
import org.exframework.support.rest.entity.ApiResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "系统")
public class PortalWebSystemController {
	
	@Autowired
	BuildService buildService;
			
	@ApiOperation(value = "更新配置(bean)")
	@RequestMapping(value = WebApiConstants.API_SYS_BUILD, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse build(@Valid @ApiParam(value = "beanid", required = true)  @NotEmpty @PathVariable String beanid) {
		buildService.build(beanid);
		return new ApiResponse();
	}
	
	@ApiOperation(value = "全量更新配置")
	@RequestMapping(value = WebApiConstants.API_SYS_RELOAD, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse reload() {
		buildService.reload();
		return new ApiResponse();
	}
}
