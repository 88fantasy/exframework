package org.exframework.portal.dao;

import org.exframework.portal.metadata.sys.Permission;

/**
 * 权限 Dao
 * @author rwe
 * @since Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PortalCorePermissionDao extends PortalCoreMetaDao<Permission> {

	String[] findTopPermissionKeys();

}
