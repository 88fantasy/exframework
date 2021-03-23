package com.gzmpc.portal.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.admin.constant.AdminApiConstants;
import com.gzmpc.portal.admin.dto.PostHovQueryRequest;
import com.gzmpc.portal.admin.service.AdminHovService;
import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.controller.HovController;
import com.gzmpc.support.rest.entity.ApiResponsePage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * Author: rwe
 * Date: Jan 28, 2021
 *
 * Copyright @ 2021 
 * 
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST  })
@RequestMapping(AdminApiConstants.API_ADMIN_PREFIX)
@Api(tags = "参照")
public class SystemHovController extends HovController {
	
	@Autowired
	AdminHovService adminHovService;

	@ApiOperation(value = "查询参照列表")
	@RequestMapping(value = WebApiConstants.API_HOV_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponsePage<Hov> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostHovQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return adminHovService.query(request);
	}
}
