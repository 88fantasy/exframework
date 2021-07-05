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

import org.exframework.portal.metadata.module.Module;
import org.exframework.portal.service.sys.ModuleService;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.portal.web.service.PortalWebBaseLoginService;
import org.exframework.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "模块")
public class PortalWebModuleController {

	@Autowired
	PortalWebBaseLoginService portalWebBaseLoginService;

	@Autowired
	ModuleService moduleService;

	@ApiOperation(value = "获取模块内容")
	@RequestMapping(value = WebApiConstants.API_MODULE_INIT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Module> init(@Valid @ApiParam(value = "code", required = true) @NotEmpty @PathVariable String code) {
		Module module = moduleService.findByKey(code);
		return new ApiResponseData<Module>(module);
	}

}
