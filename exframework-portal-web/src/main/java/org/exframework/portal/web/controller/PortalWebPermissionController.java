package org.exframework.portal.web.controller;



import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.exframework.portal.metadata.sys.Role;
import org.exframework.portal.service.sys.PortalCorePermissionService;
import org.exframework.portal.service.sys.PortalCoreRoleService;
import org.exframework.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "权限")
public class PortalWebPermissionController {
	
	@Autowired
	PortalCoreRoleService portalCoreRoleService;
	
	@Autowired
    PortalCorePermissionService portalCorePermissionService;
			
	@ApiOperation(value = "角色列表")
	@RequestMapping(value = "/permission/list/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<Collection<Role>> listRole() {
		return new ApiResponseData<>(portalCoreRoleService.getAllRoles().values());
	}
	
}
