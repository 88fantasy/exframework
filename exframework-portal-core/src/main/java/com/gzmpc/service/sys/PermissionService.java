package com.gzmpc.service.sys;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.dao.PermissionDao;
import com.gzmpc.portal.metadata.sys.Permission;
import com.gzmpc.portal.metadata.sys.SystemConst;
import com.gzmpc.portal.permission.PermissionSupport;
import com.gzmpc.support.common.annotation.BuildComponent;
import com.gzmpc.support.common.build.Buildable;


/**
 * 权限控制业务类
 * @author rwe
 *
 */
@Service
@BuildComponent
public class PermissionService implements Buildable, PermissionSupport {
	
	private Logger log = LoggerFactory.getLogger(PermissionService.class.getName());
	
	private Map<String,Permission> allPermissions = new ConcurrentHashMap<String,Permission>();
	
	@Autowired
	public SystemConst systemConst;

	@Autowired
	PermissionDao permissionDao;
	
	public Map<String, Permission> getAllPermissions() {
		return allPermissions;
	}

	public Permission getPermission(String key) {
		return allPermissions.get(key);
	}
	
	public Map<String,Permission> sum(List<Map<String,Permission>> permissionMapList) {
		Map<String, Permission> permissions = new ConcurrentHashMap<String, Permission>();
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

	@Override
	public void build(ApplicationContext ac) {
		initAllPermissions();
	}
}