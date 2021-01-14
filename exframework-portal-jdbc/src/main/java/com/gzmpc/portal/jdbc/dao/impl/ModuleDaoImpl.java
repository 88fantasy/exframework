package com.gzmpc.portal.jdbc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzmpc.portal.dao.DataItemDao;
import com.gzmpc.portal.dao.ModuleDao;
import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.module.ModuleBase;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.portal.jdbc.entity.ModuleDO;
import com.gzmpc.portal.jdbc.entity.ModuleHovDO;
import com.gzmpc.portal.jdbc.mapper.ModuleHovMapper;
import com.gzmpc.portal.jdbc.mapper.ModuleMapper;
import com.gzmpc.portal.jdbc.util.MapperUtil;

/**
 * 模块数据类 Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class ModuleDaoImpl extends MetaDaoImpl<ModuleDO, ModuleBase> implements ModuleDao, MapperUtil<ModuleDO> {

	@Autowired
	ModuleMapper moduleMapper;

	@Autowired
	ModuleHovMapper moduleHovMapper;
	
	@Autowired
	DataItemDao dataItemDao;

	@Override
	public Collection<String> allKeys() {
		return moduleMapper.selectList(new QueryWrapper<ModuleDO>().eq("valid", true)).stream().map(ModuleDO::getCode)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<String> findPermissionKeyByEntity(ModuleBase entity) {
		return Collections.emptyList();
	}

	@Override
	public Collection<String> findHovKeyByEntity(ModuleBase entity) {
		return moduleHovMapper.selectList(new QueryWrapper<ModuleHovDO>().eq("moduleKey", entity.getCode())).stream()
				.map(ModuleHovDO::getHovKey).collect(Collectors.toList());
	}

	@Override
	public Collection<DataItem> findDataItemByEntity(ModuleBase entity) {
		return dataItemDao.findExtendByObjectCode(entity.getCode());
	}

	@Override
	public BaseMapper<ModuleDO> getBaseMapper() {
		return moduleMapper;
	}

	@Override
	public ModuleDO genInstance() {
		return new ModuleDO();
	}

	@Override
	public PageModel<ModuleBase> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page) {
		Page<ModuleDO> p = moduleMapper.selectPage(new Page<ModuleDO>(page.getCurrent(), page.getPageSize()), wrapperFromCondition(params));
		return modelFromPage(p,ModuleBase.class);
	}

	@Override
	public List<ModuleBase> list(Collection<FilterCondition> params) {
		return new ArrayList<ModuleBase>(moduleMapper.selectList(wrapperFromCondition(params)));
	}


}
