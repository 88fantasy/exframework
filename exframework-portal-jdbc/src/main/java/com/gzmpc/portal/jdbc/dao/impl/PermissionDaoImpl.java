package com.gzmpc.portal.jdbc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.portal.dao.PermissionDao;
import com.gzmpc.portal.metadata.sys.Permission;
import com.gzmpc.portal.jdbc.entity.PermissionDO;
import com.gzmpc.portal.jdbc.mapper.PermissionMapper;

/**
 * 权限数据类
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class PermissionDaoImpl extends MetaDaoImpl<PermissionDO,Permission> implements PermissionDao {
	
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
		Permission entity =  super.findByKey(key);
		if(!StringUtils.isEmpty(entity.getParentPermissionKey())) {
			List<PermissionDO> children = permissionMapper.selectList(new QueryWrapper<PermissionDO>().eq("parent_permission_key", entity.getCode()));
			List<Permission> permissions = new ArrayList<Permission>(children);
			entity.setChildren(permissions);
		}
		return entity;
	}

	@Override
	public BaseMapper<PermissionDO> getBaseMapper() {
		return permissionMapper;
	}

	@Override
	public PermissionDO genInstance() {
		return new PermissionDO();
	}

	

}