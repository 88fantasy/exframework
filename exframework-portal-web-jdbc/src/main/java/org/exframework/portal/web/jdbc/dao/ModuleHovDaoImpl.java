package org.exframework.portal.web.jdbc.dao;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.exframework.portal.dao.ModuleDao;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.portal.metadata.module.ModuleBase;
import org.exframework.portal.web.dao.ModuleHovDao;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

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
		return query(conditions, page, Arrays.asList());
	}

	@Override
	public Class<ModuleBase> getEntityClass() {
		return ModuleBase.class;
	}

	@Override
	public PageModel<ModuleBase> query(Collection<FilterCondition> conditions, Page page, Collection<String> sorts) {
		return moduleDao.query(conditions, page, sorts);
	}

}
