package com.gzmpc.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.sys.AccountParameter;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.entity.ApiResponsePage;
import com.gzmpc.web.constants.WebApiConstants;
import com.gzmpc.web.entity.dto.DeleteParamRequest;
import com.gzmpc.web.entity.dto.PostParamQueryRequest;
import com.gzmpc.web.service.LoginService;
import com.gzmpc.web.service.ParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE,
		RequestMethod.POST })
@Api(tags = "参数")
public class ParamController {

	@Autowired
	LoginService loginService;

	@Autowired
	ParamService paramService;

	@ApiOperation(value = "查询参数列表")
	@RequestMapping(value = WebApiConstants.API_PARAM_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponsePage<AccountParameter> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostParamQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return paramService.query(request);
	}

	@ApiOperation(value = "查询参数列表")
	@RequestMapping(value = WebApiConstants.API_PARAM_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<String> getParamValue(
			@ApiParam(value = "参数键值", required = true) @PathVariable("key") String key,
			@ApiParam(value = "帐号", required = true) @PathVariable("account") String account) {
		if (!StringUtils.hasText(key) && !StringUtils.hasText(account)) {
			return paramService.get(key, account);
		} else {
			return ApiResponseData.paramError();
		}
	}

	@ApiOperation(value = "保存用户参数配置")
	@RequestMapping(value = WebApiConstants.API_PARAM_POST, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Boolean> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody AccountParameter dto) {
		return paramService.post(dto);
	}

	@ApiOperation(value = "删除用户参数配置")
	@RequestMapping(value = WebApiConstants.API_PARAM_DELETE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Long> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody DeleteParamRequest request) {
		return paramService.delete(request);
	}
}
