//package org.exframework.portal.web.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.exframework.portal.service.sys.ModuleService;
//import org.exframework.portal.web.constants.WebApiConstants;
//import org.exframework.support.rest.entity.ApiResponseData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotEmpty;
//
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
//@Api(tags = "模块")
//public class PortalWebModuleController {
//
//	@Autowired
//	ModuleService moduleService;
//
//	@ApiOperation(value = "获取模块内容")
//	@RequestMapping(value = WebApiConstants.API_MODULE_INIT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ApiResponseData<Module> init(@Valid @ApiParam(value = "code", required = true) @NotEmpty @PathVariable String code) {
//		Module module = moduleService.findByKey(code);
//		return new ApiResponseData<Module>(module);
//	}
//
//}
