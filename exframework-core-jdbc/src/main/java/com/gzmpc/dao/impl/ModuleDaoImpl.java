package com.gzmpc.dao.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.ModuleDO;
import com.gzmpc.core.entity.ModuleHovDO;
import com.gzmpc.core.mapper.ModuleHovMapper;
import com.gzmpc.core.mapper.ModuleMapper;
import com.gzmpc.dao.ModuleDao;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.module.ModuleEntity;

/**
 * 模块数据类 Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class ModuleDaoImpl implements ModuleDao {

	@Autowired
	ModuleMapper moduleMapper;

	@Autowired
	ModuleHovMapper moduleHovMapper;

	@Override
	public Collection<String> allKeys() {
		return moduleMapper.selectList(new QueryWrapper<ModuleDO>().eq("valid", true))
				.stream().map(ModuleDO::getCode)
				.collect(Collectors.toList());
	}

	@Override
	public ModuleEntity findByKey(String key) throws NotFoundException {
		ModuleDO entity = moduleMapper.selectOne(new QueryWrapper<ModuleDO>().eq("key", key));
		if (entity == null) {
			throw new NotFoundException();
		}
		return entity;
	}

	@Override
	public Collection<String> findPermissionKeyByEntity(ModuleEntity entity) {
		return Collections.emptyList();
	}

	@Override
	public Collection<String> findHovKeyByEntity(ModuleEntity entity) {
		return moduleHovMapper.selectList(new QueryWrapper<ModuleHovDO>().eq("moduleKey", entity.getCode())).stream()
				.map(ModuleHovDO::getHovKey).collect(Collectors.toList());
	}

	@Override
	public Collection<ModuleEntity> all() {
		// TODO Auto-generated method stub
		return null;
	}

}
