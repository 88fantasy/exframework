package org.exframework.portal.dao;

import org.exframework.portal.permission.PermissionGroup;

import java.util.Map;

/**
 * 权限组 Dao
 *
 * @author rwe
 * @date 2021/7/5 09:42
 **/
public interface PortalCorePermissionGroupDao extends PortalCoreMetaDao<PermissionGroup> {

    /**
     * 获取所有权限组
     *
     * @return
     */
    Map<String, PermissionGroup> allGroups();
}
