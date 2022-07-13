package org.exframework.portal.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.support.common.build.BuildService;
import org.exframework.support.rest.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
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
