package org.exframework.portal.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.metadata.sys.AccountParameter;
import org.exframework.portal.service.sys.PortalCoreSystemParameterService;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.support.rest.entity.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE,
		RequestMethod.POST })
@Api(tags = "参数")
public class PortalWebParamController {

	@Autowired
	PortalCoreSystemParameterService portalCoreSystemParameterService;
	
	@ApiOperation(value = "获取帐号参数")
	@RequestMapping(value = WebApiConstants.API_PARAM_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<String> getParamValue(
			@ApiParam(value = "参数键值", required = true) @PathVariable("key") String key,
			@ApiParam(value = "帐号", required = true) @PathVariable("account") String account) {
		if (!StringUtils.hasText(key) && !StringUtils.hasText(account)) {
			return new ApiResponseData<>(portalCoreSystemParameterService.getAccoutParameter(key, account));
		} else {
			return ApiResponseData.paramError();
		}
	}

	@ApiOperation(value = "保存用户参数配置")
	@RequestMapping(value = WebApiConstants.API_PARAM_POST, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Boolean> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody AccountParameter dto) {
		return new ApiResponseData<>(portalCoreSystemParameterService.putAccountKey(dto.getAccount(), dto.getCode(), dto.getName(), dto.getValue(), dto.getDescription()));
	}
}
