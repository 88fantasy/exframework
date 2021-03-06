package org.exframework.portal.service.sys;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import org.exframework.portal.dao.PortalCoreRoleDao;
import org.exframework.portal.metadata.sys.Role;
import org.exframework.portal.metadata.sys.RoleBaseAccount;

/**
 * 角色业务类 Author: rwe Date: Dec 28, 2020
 *
 * Copyright @ 2020
 * 
 */
@Service
public class PortalCoreRoleService {

	@Autowired
	PortalCoreRoleDao roleDao;
	
	@Autowired
	PortalCorePermissionService portalCorePermissionService;

	public Map<String, Role> getAllRoles() {
		Map<String, Role> allRoles = new ConcurrentHashMap<String, Role>();
		Collection<Role> roles = roleDao.all();
		for(Role role : roles) {
			allRoles.put(role.getCode(), role);
		}
		return allRoles;
	}
	
	@Nullable
	public Collection<Role> findByAccount(RoleBaseAccount account) {
		 return roleDao.findByAccount(account);
	 }

}
