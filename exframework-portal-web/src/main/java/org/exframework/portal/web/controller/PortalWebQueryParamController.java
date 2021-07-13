package org.exframework.portal.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.metadata.queryparamitem.QueryParamBase;
import org.exframework.portal.service.sys.QueryparamService;
import org.exframework.portal.web.annotation.RequireLogin;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.support.rest.entity.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "查询框")
public class PortalWebQueryParamController {

	@Autowired
	QueryparamService queryparamService;


	@RequireLogin
	@ApiOperation(value = "获取查询框")
	@RequestMapping(value = WebApiConstants.API_QUERYPARAM_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
