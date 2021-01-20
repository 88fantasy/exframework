package com.gzmpc.portal.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.metadata.queryparamitem.QueryParamBase;
import com.gzmpc.portal.service.sys.QueryparamService;
import com.gzmpc.portal.web.annotation.RequireLogin;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.service.LoginService;
import com.gzmpc.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "查询框")
public class QueryParamController {

	@Autowired
	QueryparamService queryparamService;

	@Autowired
	LoginService loginService;

	@RequireLogin
	@ApiOperation(value = "获取查询框")
	@RequestMapping(value = WebApiConstants.API_QUERYPARAM_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<QueryParamBase[]> get(
			@Valid @ApiParam(value = "formcode", required = true) @NotEmpty @PathVariable String queryparamcode) {
		if (queryparamcode != null) {
			QueryParamBase[] bases = queryparamService.get(queryparamcode);
			return new ApiResponseData<QueryParamBase[]>(bases);
		} else {
			return ApiResponseData.notEnough();
		}
	}

}