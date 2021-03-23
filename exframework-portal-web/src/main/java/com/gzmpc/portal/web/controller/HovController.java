package com.gzmpc.portal.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.dto.PostConditionQueryRequest;
import com.gzmpc.portal.web.service.WebHovService;
import com.gzmpc.support.rest.entity.ApiResponsePage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST  })
@Api(tags = "参照")
public class HovController {

	@Autowired
	WebHovService webHovService;

	@ApiOperation(value = "查询参照 by condition")
	@RequestMapping(value = WebApiConstants.API_HOV_DATA_CONDITION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponsePage<?> queryCondition(@Valid @ApiParam(value = "code", required = true) @PathVariable String code,
			@ApiParam(value = "查询dto", required = true) @Valid @RequestBody PostConditionQueryRequest request) {
		return webHovService.query(code, request);
	}
	
	@ApiOperation(value = "查询参照 by param")
	@RequestMapping(value = WebApiConstants.API_HOV_DATA_PARAM, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponsePage<?> queryParam(@Valid @ApiParam(value = "code", required = true) @PathVariable String code,
			@ApiParam(value = "查询dto", required = true) @Valid @RequestBody String requestJson) {
		return webHovService.query(code, requestJson);
	}

}
