package org.exframework.portal.dao;

import org.exframework.portal.permission.PermissionResource;

import java.util.Map;

/**
 * 权限组 Dao
 *
 * @author rwe
 * @date 2021/7/5 09:42
 **/
public interface PortalCorePermissionResourceDao extends PortalCoreMetaDao<PermissionResource> {

    /**
     * 获取所有权限组
     *
     * @return
     */
    Map<String, PermissionResource> allGroups();
}
