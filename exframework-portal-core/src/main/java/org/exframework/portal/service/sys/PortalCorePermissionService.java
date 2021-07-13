package org.exframework.portal.service.sys;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.exframework.portal.dao.PortalCorePermissionResourceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.exframework.portal.dao.PortalCorePermissionDao;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.metadata.sys.Permission;
import org.exframework.portal.metadata.sys.SystemConst;
import org.exframework.portal.permission.PermissionResource;


/**
 * 权限控制业务类
 * @author rwe
 *
 */
@Service
public class PortalCorePermissionService {
	
	private Logger log = LoggerFactory.getLogger(PortalCorePermissionService.class.getName());
	
	private Map<String,Permission> allPermissions = new ConcurrentHashMap<>();

	@Autowired
	PortalCorePermissionDao permissionDao;

	@Autowired
	PortalCorePermissionResourceDao permissionResourceDao;
	
	public Map<String, PermissionResource> getPermissionResources() {
		return permissionResourceDao.allGroups();
	}
	
	public Map<String, Permission> getAllPermissions() {
		return allPermissions;
	}

	public Permission getPermission(String key) {
		return allPermissions.get(key);
	}
	
	public Map<String,Permission> sum(List<Map<String,Permission>> permissionMapList) {
		Map<String, Permission> permissions = new ConcurrentHashMap<String, Permission>(16);
		for(Map<String,Permission> permissionMap : permissionMapList) {
			permissions.putAll(permissionMap);
		}
		return permissions;
	}

	private synchronized void initAllPermissions() {
		allPermissions.clear();
		String[] keys = permissionDao.findTopPermissionKeys();
		for(String key : keys) {
			Permission m = permissionDao.findByKey(key);
			putModuleToMap(m);
		}
	}
	
	private void putModuleToMap(Permission m) {
		String key = m.getCode();
		allPermissions.put(key, m);
		List<Permission> children = m.getChildren();
		if( children != null && children.size()>0) {
			for(int i=0,j=children.size();i<j;i++) {
				Permission c = children.get(i);
				putModuleToMap(c);
			}
		}
	}

	
	public boolean hasRight(Account account,String permissionCode) {
		Map<String, Permission> modules = account.getPermissions();
		return modules.containsKey(permissionCode);
	}
}