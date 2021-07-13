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
import org.exframework.portal.admin.dto.PostLogQueryRequest;
import org.exframework.portal.admin.service.AdminLogService;
import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.sys.Logger;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.support.rest.entity.ApiResponsePage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE,
		RequestMethod.POST })
@RequestMapping(AdminApiConstants.API_ADMIN_PREFIX)
@Api(tags = "日志")
public class PortalAdminLogController {

	@Autowired
	AdminLogService adminLogService;

	@ApiOperation(value = "查询日志列表")
	@RequestMapping(value = WebApiConstants.API_LOG_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponsePage<Logger> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostLogQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return adminLogService.query(request);
	}
}
