package org.exframework.portal.dao;

import java.util.Collection;

import org.springframework.lang.Nullable;

import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.metadata.sys.Role;
import org.exframework.portal.metadata.sys.RoleBaseAccount;

/**
 * 角色数据类
 * @author rwe
 * @since Dec 28, 2020
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
	
	/**
	 * 获取角色关联的帐号
	 * @param account
	 * @return
	 */
	@Nullable
	Collection<Account> findAccountByRole(Role role);
	
	
	
}
