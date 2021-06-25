package com.gzmpc.portal.web.controller;



import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.metadata.sys.Role;
import com.gzmpc.portal.service.sys.PermissionService;
import com.gzmpc.portal.service.sys.RoleService;
import com.gzmpc.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "权限")
public class PermissionController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PermissionService permissionService;
			
	@ApiOperation(value = "角色列表")
	@RequestMapping(value = "/permission/list/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Collection<Role>> listRole() {
		return new ApiResponseData<>(roleService.getAllRoles().values());
	}
	
}
