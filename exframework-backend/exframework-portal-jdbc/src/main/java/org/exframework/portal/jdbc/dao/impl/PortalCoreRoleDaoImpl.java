package org.exframework.portal.jdbc.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.exframework.portal.dao.PortalCoreRoleDao;
import org.exframework.portal.jdbc.entity.security.AccountRoleDO;
import org.exframework.portal.jdbc.entity.security.RoleDO;
import org.exframework.portal.jdbc.entity.security.RolePermissionDO;
import org.exframework.portal.jdbc.mapper.AccountRoleMapper;
import org.exframework.portal.jdbc.mapper.RoleMapper;
import org.exframework.portal.jdbc.mapper.RolePermissionMapper;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.metadata.sys.Permission;
import org.exframework.portal.metadata.sys.Role;
import org.exframework.portal.metadata.sys.RoleBaseAccount;
import org.exframework.portal.service.sys.PortalCoreAccountService;
import org.exframework.portal.service.sys.PortalCorePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 角色数据类
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class PortalCoreRoleDaoImpl extends PortalCoreMetaDaoImpl<RoleDO,Role> implements PortalCoreRoleDao {
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	AccountRoleMapper accountRoleMapper;
	
	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Autowired
	PortalCorePermissionService portalCorePermissionService;



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
			List<Role> roles = new ArrayList<>();
			for(RoleDO child : childrens) {
				Role childRole = findByKey(child.getCode());
				roles.add(childRole);
			}
			role.setChildren(roles);
			List<Map<String, Permission>> permissionMapList = roles.stream().map(Role::getPermissionMap).collect(Collectors.toList());
			Map<String, Permission> ms = portalCorePermissionService.sum(permissionMapList);
			role.setPermissionMap(ms);
		}
		return role;
	}

	@Override
	public Collection<Role> findByAccount(RoleBaseAccount account) {
		List<Role> roles = new ArrayList<>();
		List<AccountRoleDO> relates = accountRoleMapper.selectList(Wrappers.<AccountRoleDO>lambdaQuery().eq(AccountRoleDO::getAccount, account.getAccount()));
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
		RoleDO entity = roleMapper.selectOne(Wrappers.<RoleDO>lambdaQuery().eq(RoleDO::getCode, key));
		if(entity != null) {
			List<String> permissionKeys = rolePermissionMapper.selectList(Wrappers.<RolePermissionDO>lambdaQuery().eq(RolePermissionDO::getRole, entity.getCode()))
					.stream().map(RolePermissionDO::getPermission).collect(Collectors.toList());
			Map<String,Permission> permissionMap = new ConcurrentHashMap<>();
			for(String permission : permissionKeys) {
				permissionMap.put(permission, portalCorePermissionService.getPermission(permission));
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

	@Override
	public Role transform(RoleDO roleDO) {
		return roleDO;
	}

}
