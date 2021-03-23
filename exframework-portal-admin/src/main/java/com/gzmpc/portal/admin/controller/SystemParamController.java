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
import com.gzmpc.portal.admin.dto.DeleteParamRequest;
import com.gzmpc.portal.admin.service.AdminParamService;
import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.sys.AccountParameter;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.controller.ParamController;
import com.gzmpc.portal.web.dto.PostConditionQueryRequest;
import com.gzmpc.portal.web.service.LoginService;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.entity.ApiResponsePage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE,
		RequestMethod.POST })
@RequestMapping(AdminApiConstants.API_ADMIN_PREFIX)
@Api(tags = "参数")
public class SystemParamController extends ParamController {

	@Autowired
	LoginService loginService;

	@Autowired
	AdminParamService paramService;

	@ApiOperation(value = "查询参数列表")
	@RequestMapping(value = WebApiConstants.API_PARAM_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponsePage<AccountParameter> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostConditionQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return paramService.query(request);
	}

	@ApiOperation(value = "删除用户参数配置")
	@RequestMapping(value = WebApiConstants.API_PARAM_DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Long> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody DeleteParamRequest request) {
		return paramService.delete(request);
	}
}
