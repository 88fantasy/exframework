package com.gzmpc.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.service.sys.DdlService;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.web.constants.WebApiConstants;
import com.gzmpc.web.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "下拉列表")
public class DdlController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	DdlService ddl;
	
	
	@ApiOperation(value = "获取下拉列表")
	@RequestMapping(value = WebApiConstants.API_DDL_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Map<String,String>> get(@ApiParam(value = "ddlkey", required = true) @PathVariable String ddlkey) throws NotAuthorizedException, NotFoundException {
		if( ddlkey != null ) {
			Map<String, String> result = ddl.get(ddlkey);
			return new ApiResponseData<Map<String,String>>(result);
		}
		else {
			return ApiResponseData.notEnough();
		}
	}
	
}
