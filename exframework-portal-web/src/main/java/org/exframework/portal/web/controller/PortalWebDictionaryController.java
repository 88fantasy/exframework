package org.exframework.portal.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.service.sys.PortalCoreDdlService;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.portal.web.dto.OptionsResponse;
import org.exframework.portal.web.service.PortalWebDdlService;
import org.exframework.support.rest.entity.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST })
@Api(tags = "下拉列表")
public class PortalWebDictionaryController {
	
	@Autowired
    PortalCoreDdlService portalCoreDdlService;
	
	@Autowired
	PortalWebDdlService portalWebDdlService;
	
	
	@ApiOperation(value = "获取下拉列表")
	@RequestMapping(value = WebApiConstants.API_DDL_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Map<String,String>> get(@ApiParam(value = "ddlkey", required = true) @PathVariable String ddlkey) throws NotAuthorizedException, NotFoundException {
		if( ddlkey != null ) {
			Map<String, String> result = portalCoreDdlService.get(ddlkey);
			return new ApiResponseData<Map<String,String>>(result);
		}
		else {
			return ApiResponseData.notEnough();
		}
	}
	
	@ApiOperation(value = "获取多个下拉列表")
	@RequestMapping(value = WebApiConstants.API_DDL_MANY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Map<String,Map<String,String>>>  many(@ApiParam(value = "keys", required = true) @Valid  @RequestBody(required = true) String[] keys) throws NotAuthorizedException, NotFoundException {
		return new ApiResponseData<>(portalCoreDdlService.many(keys));
	}
	
	@ApiOperation(value = "获取下拉选项")
	@RequestMapping(value = WebApiConstants.API_DDL_OPTIONS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OptionsResponse[] options(@ApiParam(value = "ddlkey", required = true) @PathVariable(required = true) String ddlkey) throws NotAuthorizedException, NotFoundException {
		return portalWebDdlService.options(ddlkey);
	}
	
	@ApiOperation(value = "获取多个下拉选项")
	@RequestMapping(value = WebApiConstants.API_DDL_MANY_OPTIONS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Map<String,OptionsResponse[]>> manyOptions(@ApiParam(value = "keys", required = true) @Valid  @RequestBody(required = true) String[] keys) throws NotAuthorizedException, NotFoundException {
		return new ApiResponseData<>(portalWebDdlService.manyOptions(keys));
	}
}
