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
import com.gzmpc.portal.admin.dto.PostDataItemPostRequest;
import com.gzmpc.portal.admin.dto.PostDataItemQueryRequest;
import com.gzmpc.portal.admin.service.AdminDataItemService;
import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.web.constants.WebApiConstants;
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
@Api(tags = "数据项")
public class SystemDataItemController {

	@Autowired
	LoginService loginService;

	@Autowired
	AdminDataItemService dataItemService;

	@ApiOperation(value = "查询字典列表")
	@RequestMapping(value = WebApiConstants.API_DATAITEM_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponsePage<DataItem> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostDataItemQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return dataItemService.query(request);
	}

	@ApiOperation(value = "新增或修改字典")
	@RequestMapping(value = WebApiConstants.API_DATAITEM_POST, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Boolean> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody PostDataItemPostRequest request) {
		return dataItemService.post(request);
	}
}
