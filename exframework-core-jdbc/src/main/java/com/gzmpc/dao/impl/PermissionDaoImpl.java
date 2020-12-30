package com.gzmpc.dao.impl;

import java.util.ArrayList;
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
import com.gzmpc.core.entity.mapper.PermissionMapper;
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
		List<PermissionDO> permissions = permissionMapper.list(new QueryWrapper<PermissionDO>().isNull("parent_permission_key"));
		return permissions.stream().map(Permission::getKey).collect(Collectors.toList()).toArray(new String[permissions.size()]);
	}

	@Override
	public Permission findByKey(String key) {
		PermissionDO p =  permissionMapper.getOne(new QueryWrapper<PermissionDO>().eq("key", key));
		if(!StringUtils.isEmpty(p.getParentPermissionKey())) {
			List<PermissionDO> children = permissionMapper.list(new QueryWrapper<PermissionDO>().eq("parent_permission_key", p.getKey()));
			p.setChildren(new ArrayList<Permission>(children));
		}
		return p;
	}

	@Override
	public Collection<String> allKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Permission> all() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
