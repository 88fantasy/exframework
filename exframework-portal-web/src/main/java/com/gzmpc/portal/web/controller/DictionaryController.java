package com.gzmpc.portal.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.service.sys.DdlService;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.dto.OptionsResponse;
import com.gzmpc.portal.web.service.LoginService;
import com.gzmpc.portal.web.service.WebDdlService;
import com.gzmpc.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST })
@Api(tags = "下拉列表")
public class DictionaryController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	DdlService ddlService;
	
	@Autowired
	WebDdlService webDdlService;
	
	
	@ApiOperation(value = "获取下拉列表")
	@RequestMapping(value = WebApiConstants.API_DDL_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Map<String,String>> get(@ApiParam(value = "ddlkey", required = true) @PathVariable String ddlkey) throws NotAuthorizedException, NotFoundException {
		if( ddlkey != null ) {
			Map<String, String> result = ddlService.get(ddlkey);
			return new ApiResponseData<Map<String,String>>(result);
		}
		else {
			return ApiResponseData.notEnough();
		}
	}
	
	@ApiOperation(value = "获取多个下拉列表")
	@RequestMapping(value = WebApiConstants.API_DDL_MANY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Map<String,Map<String,String>>>  many(@ApiParam(value = "keys", required = true) @Valid  @RequestBody(required = true) String[] keys) throws NotAuthorizedException, NotFoundException {
		return new ApiResponseData<>(ddlService.many(keys));
	}
	
	@ApiOperation(value = "获取下拉选项")
	@RequestMapping(value = WebApiConstants.API_DDL_OPTIONS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public OptionsResponse[] options(@ApiParam(value = "ddlkey", required = true) @PathVariable(required = true) String ddlkey) throws NotAuthorizedException, NotFoundException {
		return webDdlService.options(ddlkey);
	}
	
	@ApiOperation(value = "获取多个下拉选项")
	@RequestMapping(value = WebApiConstants.API_DDL_MANY_OPTIONS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Map<String,OptionsResponse[]>> manyOptions(@ApiParam(value = "keys", required = true) @Valid  @RequestBody(required = true) String[] keys) throws NotAuthorizedException, NotFoundException {
		return new ApiResponseData<>(webDdlService.manyOptions(keys));
	}
}
