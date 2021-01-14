package com.gzmpc.portal.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.service.sys.DdlService;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.service.LoginService;
import com.gzmpc.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "下拉列表")
public class DictionaryController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	DdlService ddlService;
	
	
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
	
}
