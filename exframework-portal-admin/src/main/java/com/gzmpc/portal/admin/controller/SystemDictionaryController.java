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
import com.gzmpc.portal.admin.dto.PostDictionaryPostRequest;
import com.gzmpc.portal.admin.dto.PostDictionaryQueryRequest;
import com.gzmpc.portal.admin.service.AdminDictionaryService;
import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.dict.DictionaryItem;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.controller.DictionaryController;
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
@Api(tags = "字典")
public class SystemDictionaryController extends DictionaryController {

	@Autowired
	LoginService loginService;

	@Autowired
	AdminDictionaryService dictionaryService;

	@ApiOperation(value = "查询字典列表")
	@RequestMapping(value = WebApiConstants.API_DDL_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponsePage<DictionaryItem> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostDictionaryQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return dictionaryService.query(request);
	}

	@ApiOperation(value = "新增或修改字典")
	@RequestMapping(value = WebApiConstants.API_DDL_POST, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Boolean> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody PostDictionaryPostRequest request) {
		return dictionaryService.post(request);
	}
}
