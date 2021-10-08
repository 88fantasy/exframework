package org.exframework.portal.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.admin.constant.AdminApiConstants;
import org.exframework.portal.admin.dto.DeleteParamRequest;
import org.exframework.portal.admin.service.AdminParamService;
import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.sys.AccountParameter;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.portal.web.controller.PortalWebParamController;
import org.exframework.portal.web.dto.PostConditionQueryRequest;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.entity.ApiResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE,
		RequestMethod.POST })
@RequestMapping(AdminApiConstants.API_ADMIN_PREFIX)
@Api(tags = "参数")
public class PortalAdminParamController extends PortalWebParamController {

	@Autowired
	AdminParamService paramService;

	@ApiOperation(value = "查询参数列表")
	@RequestMapping(value = WebApiConstants.API_PARAM_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponsePage<AccountParameter> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostConditionQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return paramService.query(request);
	}

	@ApiOperation(value = "删除用户参数配置")
	@RequestMapping(value = WebApiConstants.API_PARAM_DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Long> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody DeleteParamRequest request) {
		return paramService.delete(request);
	}
}
