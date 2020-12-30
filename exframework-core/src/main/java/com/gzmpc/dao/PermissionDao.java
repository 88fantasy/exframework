package com.gzmpc.dao;

import com.gzmpc.metadata.sys.Permission;

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
	
	Permission findByKey(String key);
}
