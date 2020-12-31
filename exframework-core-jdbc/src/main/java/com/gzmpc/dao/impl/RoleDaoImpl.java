package com.gzmpc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.AccountRoleDO;
import com.gzmpc.core.entity.RoleDO;
import com.gzmpc.core.entity.RolePermissionDO;
import com.gzmpc.core.mapper.AccountRoleMapper;
import com.gzmpc.core.mapper.RoleMapper;
import com.gzmpc.core.mapper.RolePermissionMapper;
import com.gzmpc.dao.RoleDao;
import com.gzmpc.metadata.sys.Permission;
import com.gzmpc.metadata.sys.Role;
import com.gzmpc.metadata.sys.RoleBaseAccount;
import com.gzmpc.service.sys.PermissionService;

/**
 * 角色数据类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	AccountRoleMapper accountRoleMapper;
	
	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Autowired
	PermissionService permissionService;
	


	@Override
	public Collection<String> allKeys() {
		return all().stream().map(Role::getCode).collect(Collectors.toList());
	}

	@Override
	public Collection<Role> all() {
		List<RoleDO> entities = roleMapper.list();
		return entities.stream().map(entity -> (Role) entity).collect(Collectors.toList());
	}
	
	@Override
	public Role findByKey(String key) {
		Role role = getByKey(key);
		if(role != null) {
			List<RoleDO> childrens = roleMapper.list(new QueryWrapper<RoleDO>().eq("parentKey", key));
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
		List<AccountRoleDO> relates = accountRoleMapper.selectList(new QueryWrapper<AccountRoleDO>().eq("role", account.getAccountId()));
		for(AccountRoleDO ar : relates) {
			String roleKey = ar.getRole();
			Role role = findByKey(roleKey);
			roles.add(role);
		}
		return roles;
	}

	/**
	 * 查询单个角色(不包含子角色)
	 * @param key
	 * @return
	 */
	private Role getByKey(String key){
		RoleDO entity = roleMapper.getOne(new QueryWrapper<RoleDO>().eq("key", key));
		if(entity != null) {
			List<String> permissionKeys = rolePermissionMapper.selectList(new QueryWrapper<RolePermissionDO>().eq("role", entity.getCode()))
					.stream().map(RolePermissionDO::getPermission).collect(Collectors.toList());;
			Map<String,Permission> permissionMap = new ConcurrentHashMap<String,Permission>();
			for(String permission : permissionKeys) {
				permissionMap.put(permission, permissionService.getPermission(permission));
			}
			entity.setPermissionMap(permissionMap);
		}
		return entity;
	}

}
