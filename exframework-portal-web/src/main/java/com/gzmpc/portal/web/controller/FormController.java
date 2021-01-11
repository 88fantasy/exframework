package com.gzmpc.portal.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.attribute.Attribute;
import com.gzmpc.portal.metadata.form.Form;
import com.gzmpc.portal.permission.PermissionSupport;
import com.gzmpc.portal.web.annotation.RequireLogin;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.service.LoginService;
import com.gzmpc.service.sys.FormService;
import com.gzmpc.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "表单")
public class FormController {
	
	@Autowired
	PermissionSupport permissions;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	FormService formService;

	@RequireLogin
	@ApiOperation(value = "获取表单属性")
	@RequestMapping(value = WebApiConstants.API_FORM_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<List<Map<String,Object>>> get(@Valid @ApiParam(value = "formcode", required = true)  @NotEmpty @PathVariable String formcode) throws NotFoundException {
		
		Form form = formService.findByKey(formcode);
		if (form == null) {
			throw new NotFoundException("根据编码(" + formcode + ")找不到对应的表单定义！！！");
		}
		Attribute[] attrs = form.getAttributes();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0,j=attrs.length;i<j;i++) {
			Attribute attr = attrs[i];
			list.add(formService.showAttr(form, attr));
		}
		return new ApiResponseData<List<Map<String,Object>>>(list);
	}
	
}
