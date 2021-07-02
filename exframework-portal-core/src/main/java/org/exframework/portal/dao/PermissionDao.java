package org.exframework.portal.dao;

import java.util.Map;

import org.exframework.portal.metadata.sys.Permission;
import org.exframework.portal.permission.PermissionGroup;

/**
 * 权限 Dao
 * @author rwe
 * @since Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PermissionDao extends MetaDao<Permission> {

	String[] findTopPermissionKeys();
	
	Permission findByKey(String code);
	
	Map<String, PermissionGroup> allGroups();
	
}
