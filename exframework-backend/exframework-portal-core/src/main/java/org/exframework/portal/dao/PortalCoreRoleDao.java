package org.exframework.portal.dao;

import org.exframework.portal.metadata.sys.Role;
import org.exframework.portal.metadata.sys.RoleBaseAccount;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * 角色数据类
 *
 * @author rwe
 * @since Dec 28, 2020
 * <p>
 * Copyright @ 2020
 */
public interface PortalCoreRoleDao extends PortalCoreMetaDao<Role> {

    /**
     * 获取帐号关联的角色
     *
     * @param account 账号
     * @return 角色集合
     */
    @Nullable
    Collection<Role> findByAccount(RoleBaseAccount account);


}
