package org.exframework.portal.admin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.admin.constant.AdminApiConstants;
import org.exframework.portal.admin.dto.PostDataItemPostRequest;
import org.exframework.portal.admin.dto.PostDataItemQueryRequest;
import org.exframework.portal.admin.service.AdminDataItemService;
import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.entity.ApiResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(AdminApiConstants.API_ADMIN_PREFIX)
@Api(tags = "数据项")
public class PortalAdminDataItemController {

	@Autowired
	AdminDataItemService dataItemService;

	@ApiOperation(value = "查询字典列表")
	@RequestMapping(value = WebApiConstants.API_DATAITEM_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponsePage<DataItem> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostDataItemQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return dataItemService.query(request);
	}

	@ApiOperation(value = "新增或修改字典")
	@RequestMapping(value = WebApiConstants.API_DATAITEM_POST, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Boolean> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody PostDataItemPostRequest request) {
		return dataItemService.post(request);
	}
}
