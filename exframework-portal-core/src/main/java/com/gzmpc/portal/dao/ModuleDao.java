package com.gzmpc.portal.dao;

import java.util.Collection;
import java.util.List;

import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.module.ModuleBase;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface ModuleDao extends MetaDao<ModuleBase> {
	
	Collection<DataItem> findDataItemByEntity(ModuleBase entity);
	
	Collection<String> findPermissionKeyByEntity(ModuleBase entity);
	
	Collection<String> findHovKeyByEntity(ModuleBase entity);
	
	PageModel<ModuleBase> query(Collection<FilterCondition> params, Page page);
	
	List<ModuleBase> list(Collection<FilterCondition> params);
}
