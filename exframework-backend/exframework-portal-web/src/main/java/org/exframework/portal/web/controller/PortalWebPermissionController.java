package org.exframework.portal.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.exframework.portal.metadata.sys.Role;
import org.exframework.portal.service.sys.PortalCoreRoleService;
import org.exframework.support.rest.entity.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Api(tags = "权限")
public class PortalWebPermissionController {

    @Autowired
    PortalCoreRoleService portalCoreRoleService;

    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/permission/list/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseData<Collection<Role>> listRole() {
        return new ApiResponseData<>(portalCoreRoleService.getAllRoles().values());
    }

}
