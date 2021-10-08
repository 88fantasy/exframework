package org.exframework.portal.admin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.admin.constant.AdminApiConstants;
import org.exframework.portal.admin.dto.PostDictionaryPostRequest;
import org.exframework.portal.admin.dto.PostDictionaryQueryRequest;
import org.exframework.portal.admin.service.AdminDictionaryService;
import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.dict.DictionaryItem;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.portal.web.controller.PortalWebDictionaryController;
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
@Api(tags = "字典")
public class PortalAdminDictionaryController extends PortalWebDictionaryController {

	@Autowired
	AdminDictionaryService dictionaryService;

	@ApiOperation(value = "查询字典列表")
	@RequestMapping(value = WebApiConstants.API_DDL_QUERY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponsePage<DictionaryItem> queryList(
			@ApiParam(value = "dto", required = true) @Valid @RequestBody PostDictionaryQueryRequest request)
			throws NotAuthorizedException, NotFoundException {
		return dictionaryService.query(request);
	}

	@ApiOperation(value = "新增或修改字典")
	@RequestMapping(value = WebApiConstants.API_DDL_POST, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Boolean> post(
			@ApiParam(value = "保存dto", required = true) @Valid @RequestBody PostDictionaryPostRequest request) {
		return dictionaryService.post(request);
	}
}
