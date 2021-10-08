package org.exframework.portal.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.exframework.portal.admin.constant.AdminApiConstants;
import org.exframework.portal.admin.dto.PostHovQueryRequest;
import org.exframework.portal.admin.service.AdminHovService;
import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.hov.Hov;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.portal.web.controller.PortalWebHovController;
import org.exframework.support.rest.entity.ApiResponsePage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @author rwe
 * @since Jan 28, 2021
 *
 * Copyright @ 2021 
 * 
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST  })
@RequestMapping(AdminApiConstants.API_ADMIN_PREFIX)
@Api(tags = "参照")
public class PortalAdminHovController extends PortalWebHovController {
	
	@Autowired
	AdminHovService adminHovService;

	@ApiOperation(value = "查询参照列表")
	@RequestMapping(value = WebApiConstants.API_HOV_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponsePage<Hov> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostHovQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return adminHovService.query(request);
	}
}
