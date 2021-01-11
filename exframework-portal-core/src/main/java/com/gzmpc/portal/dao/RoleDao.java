package com.gzmpc.portal.dao;

import java.util.Collection;

import org.springframework.lang.Nullable;

import com.gzmpc.portal.metadata.sys.Role;
import com.gzmpc.portal.metadata.sys.RoleBaseAccount;

/**
 * 角色数据类
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface RoleDao extends MetaDao<Role> {

	/**
	 * 获取帐号关联的角色
	 * @param account
	 * @return
	 */
	@Nullable
	Collection<Role> findByAccount(RoleBaseAccount account);
	
}
