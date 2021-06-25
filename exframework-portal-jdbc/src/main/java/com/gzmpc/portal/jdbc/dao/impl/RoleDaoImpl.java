package com.gzmpc.portal.jdbc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gzmpc.portal.dao.RoleDao;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.metadata.sys.Permission;
import com.gzmpc.portal.metadata.sys.Role;
import com.gzmpc.portal.metadata.sys.RoleBaseAccount;
import com.gzmpc.portal.service.sys.AccountService;
import com.gzmpc.portal.service.sys.PermissionService;
import com.gzmpc.portal.jdbc.entity.AccountRoleDO;
import com.gzmpc.portal.jdbc.entity.RoleDO;
import com.gzmpc.portal.jdbc.entity.RolePermissionDO;
import com.gzmpc.portal.jdbc.mapper.AccountRoleMapper;
import com.gzmpc.portal.jdbc.mapper.RoleMapper;
import com.gzmpc.portal.jdbc.mapper.RolePermissionMapper;

/**
 * 角色数据类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class RoleDaoImpl extends MetaDaoImpl<RoleDO,Role> implements RoleDao {
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	AccountRoleMapper accountRoleMapper;
	
	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Autowired
	PermissionService permissionService;
	

	@Autowired
	AccountService accountService;

	@Override
	public Collection<String> allKeys() {
		return all().stream().map(Role::getCode).collect(Collectors.toList());
	}

	@Override
	public Collection<Role> all() {
		List<RoleDO> entities = roleMapper.selectList(null);
		return entities.stream().map(entity -> (Role) entity).collect(Collectors.toList());
	}
	
	@Override
	public Role findByKey(String key) {
		Role role = getByKey(key);
		if(role != null) {
			List<RoleDO> childrens = roleMapper.selectList(new QueryWrapper<RoleDO>().eq("parentKey", key));
			List<Role> roles = new ArrayList<Role>();
			for(RoleDO child : childrens) {
				Role childRole = findByKey(child.getCode());
				roles.add(childRole);
			}
			role.setChildren(roles);
			List<Map<String, Permission>> permissionMapList = roles.stream().map(Role::getPermissionMap).collect(Collectors.toList());
			Map<String, Permission> ms = permissionService.sum(permissionMapList);
			role.setPermissionMap(ms);
		}
		return role;
	}

	@Override
	public Collection<Role> findByAccount(RoleBaseAccount account) {
		List<Role> roles = new ArrayList<Role>();
		List<AccountRoleDO> relates = accountRoleMapper.selectList(Wrappers.<AccountRoleDO>lambdaQuery().eq(AccountRoleDO::getAccount, account.getAccount()));
		for(AccountRoleDO ar : relates) {
			String roleKey = ar.getRole();
			Role role = findByKey(roleKey);
			roles.add(role);
		}
		return roles;
	}
	


	@Override
	public Collection<Account> findAccountByRole(Role role) {
		List<AccountRoleDO> accounts = accountRoleMapper.selectList(Wrappers.<AccountRoleDO>lambdaQuery().eq(AccountRoleDO::getRole, role.getCode()));
		return accounts.stream().map(account -> accountService.getAccount(account.getAccount())).collect(Collectors.toList());
	}

	/**
	 * 查询单个角色(不包含子角色)
	 * @param key
	 * @return
	 */
	private Role getByKey(String key){
		RoleDO entity = roleMapper.selectOne(Wrappers.<RoleDO>lambdaQuery().eq(RoleDO::getCode, key));
		if(entity != null) {
			List<String> permissionKeys = rolePermissionMapper.selectList(Wrappers.<RolePermissionDO>lambdaQuery().eq(RolePermissionDO::getRole, entity.getCode()))
					.stream().map(RolePermissionDO::getPermission).collect(Collectors.toList());;
			Map<String,Permission> permissionMap = new ConcurrentHashMap<String,Permission>();
			for(String permission : permissionKeys) {
				permissionMap.put(permission, permissionService.getPermission(permission));
			}
			entity.setPermissionMap(permissionMap);
		}
		return entity;
	}


	@Override
	public BaseMapper<RoleDO> getBaseMapper() {
		return roleMapper;
	}

	@Override
	public RoleDO genInstance() {
		return new RoleDO();
	}

}
