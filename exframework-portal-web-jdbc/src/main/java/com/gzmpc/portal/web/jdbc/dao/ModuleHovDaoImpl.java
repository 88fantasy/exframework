package com.gzmpc.portal.web.jdbc.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzmpc.portal.dao.ModuleDao;
import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.module.ModuleBase;
import com.gzmpc.portal.web.dao.ModuleHovDao;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;

/**
 *
 * Author: rwe
 * Date: Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Repository
public class ModuleHovDaoImpl implements ModuleHovDao {
	
	@Autowired
	ModuleDao moduleDao;

	@Override
	public PageModel<ModuleBase> query(Collection<FilterCondition> conditions, Page page) {
		return moduleDao.query(conditions, page);
	}

	@Override
	public Class<ModuleBase> getEntityClass() {
		return ModuleBase.class;
	}

}
