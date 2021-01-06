package com.gzmpc.dao.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.core.entity.ModuleDO;
import com.gzmpc.core.entity.ModuleHovDO;
import com.gzmpc.core.mapper.ModuleHovMapper;
import com.gzmpc.core.mapper.ModuleMapper;
import com.gzmpc.dao.ModuleDao;
import com.gzmpc.metadata.module.Module;

/**
 * 模块数据类 Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class ModuleDaoImpl extends MetaDaoImpl<ModuleDO, Module> implements ModuleDao {

	@Autowired
	ModuleMapper moduleMapper;

	@Autowired
	ModuleHovMapper moduleHovMapper;

	@Override
	public Collection<String> allKeys() {
		return moduleMapper.selectList(new QueryWrapper<ModuleDO>().eq("valid", true)).stream().map(ModuleDO::getCode)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<String> findPermissionKeyByEntity(Module entity) {
		return Collections.emptyList();
	}

	@Override
	public Collection<String> findHovKeyByEntity(Module entity) {
		return moduleHovMapper.selectList(new QueryWrapper<ModuleHovDO>().eq("moduleKey", entity.getCode())).stream()
				.map(ModuleHovDO::getHovKey).collect(Collectors.toList());
	}

	@Override
	public BaseMapper<ModuleDO> getBaseMapper() {
		return moduleMapper;
	}

	@Override
	public ModuleDO genInstance() {
		return new ModuleDO();
	}

}
