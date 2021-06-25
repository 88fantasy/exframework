package org.exframework.portal.dao;

import org.exframework.portal.metadata.sys.Permission;

/**
 * 权限 Dao
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PermissionDao extends MetaDao<Permission> {

	String[] findTopPermissionKeys();
	
	Permission findByKey(String code);
}
