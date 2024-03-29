package org.exframework.portal.jdbc.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exframework.portal.dao.PortalCoreDataItemDao;
import org.exframework.portal.dao.PortalCoreModuleDao;
import org.exframework.portal.jdbc.entity.module.ModuleDO;
import org.exframework.portal.jdbc.entity.module.ModuleHovDO;
import org.exframework.portal.jdbc.mapper.ModuleHovMapper;
import org.exframework.portal.jdbc.mapper.ModuleMapper;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.module.ModuleBase;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模块数据类 Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class PortalCoreModuleDaoImpl extends PortalCoreMetaDaoImpl<ModuleDO, ModuleBase> implements PortalCoreModuleDao {

	@Autowired
	ModuleMapper moduleMapper;

	@Autowired
	ModuleHovMapper moduleHovMapper;
	
	@Autowired
	PortalCoreDataItemDao portalCoreDataItemDao;

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
		return portalCoreDataItemDao.findExtendByObjectCode(entity.getCode());
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
	public ModuleBase transform(ModuleDO moduleDO) {
		return moduleDO;
	}

	@Override
	public PageModel<ModuleBase> query(Collection<FilterCondition> params, Page page) {
		return moduleMapper.query(params, page, ModuleBase.class);
	}
	
	@Override
	public PageModel<ModuleBase> query(Collection<FilterCondition> params, Page page, Collection<String> sorts) {
		return moduleMapper.query(params, page, sorts, ModuleBase.class);
	}

	@Override
	public List<ModuleBase> list(Collection<FilterCondition> params) {
		return moduleMapper.list(params, ModuleBase.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insertModule(ModuleBase module) {
		String code = module.getCode();
		ModuleBase base = findByKey(code);
		if(base != null) {
			return false;
		}
		else {
			base = module;
			int in = insert(base);
			if(in > 0) {
//				Collection<Hov> hovs = module.getHovs();
//				for(Hov hov : hovs) {
//					ModuleHovDO mh = new ModuleHovDO();
//					mh.setModuleKey(module.getCode());
//					mh.setHovKey(hov.getCode());
//				}
//				module.getDataItems();
			}
		}
		return true;
	}

	@Override
	public boolean updateModule(ModuleBase module) {

		return false;
	}


}
