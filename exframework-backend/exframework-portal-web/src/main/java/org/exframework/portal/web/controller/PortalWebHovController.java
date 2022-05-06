package org.exframework.portal.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.support.rest.entity.ConditionQueryPageRequest;
import org.exframework.portal.web.service.PortalWebHovService;
import org.exframework.support.rest.entity.ApiResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST  })
@Api(tags = "参照")
public class PortalWebHovController {

	@Autowired
	PortalWebHovService portalWebHovService;

	@ApiOperation(value = "查询参照 by condition")
	@RequestMapping(value = WebApiConstants.API_HOV_DATA_CONDITION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponsePage<?> queryCondition(@Valid @ApiParam(value = "code", required = true) @PathVariable String code,
			@ApiParam(value = "查询dto", required = true) @Valid @RequestBody ConditionQueryPageRequest request) {
		return portalWebHovService.query(code, request);
	}
	
	@ApiOperation(value = "查询参照 by param")
	@RequestMapping(value = WebApiConstants.API_HOV_DATA_PARAM, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponsePage<?> queryParam(@Valid @ApiParam(value = "code", required = true) @PathVariable String code,
			@ApiParam(value = "查询dto", required = true) @Valid @RequestBody String requestJson) {
		return portalWebHovService.query(code, requestJson);
	}

}
