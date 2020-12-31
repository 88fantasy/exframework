package com.gzmpc.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.PermissionDO;
import com.gzmpc.core.mapper.PermissionMapper;
import com.gzmpc.dao.PermissionDao;
import com.gzmpc.metadata.sys.Permission;

/**
 * 权限数据类
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class PermissionDaoImpl implements PermissionDao {
	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public String[] findTopPermissionKeys() {
		List<PermissionDO> permissions = permissionMapper.selectList(new QueryWrapper<PermissionDO>().isNull("parent_permission_key"));
		return permissions.stream().map(PermissionDO::getCode).collect(Collectors.toList()).toArray(new String[permissions.size()]);
	}

	@Override
	public Permission findByKey(String key) {
		PermissionDO entity =  permissionMapper.selectOne(new QueryWrapper<PermissionDO>().eq("key", key));
		Permission p = entity.mapper(Permission.class);
		if(!StringUtils.isEmpty(entity.getParentPermissionKey())) {
			List<PermissionDO> children = permissionMapper.selectList(new QueryWrapper<PermissionDO>().eq("parent_permission_key", p.getCode()));
			List<Permission> permissions = children.stream().map(e -> e.mapper(Permission.class)).collect(Collectors.toList());
			p.setChildren(permissions);
		}
		return p;
	}

	@Override
	public Collection<String> allKeys() {
		return all().stream().map(Permission::getCode).collect(Collectors.toList());
	}

	@Override
	public Collection<Permission> all() {
		return permissionMapper.selectList(null).stream().map(e -> e.mapper(Permission.class)).collect(Collectors.toList());
	}

	

}
