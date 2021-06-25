package com.gzmpc.portal.service.sys;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.dao.RoleDao;
import com.gzmpc.portal.metadata.sys.Role;
import com.gzmpc.portal.metadata.sys.RoleBaseAccount;

/**
 * 角色业务类 Author: rwe Date: Dec 28, 2020
 *
 * Copyright @ 2020
 * 
 */
@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;
	
	@Autowired
	PermissionService permissionService;

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
