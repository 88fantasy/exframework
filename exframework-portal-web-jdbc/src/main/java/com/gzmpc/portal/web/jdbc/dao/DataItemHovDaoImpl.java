package com.gzmpc.portal.web.jdbc.dao;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzmpc.portal.dao.DataItemDao;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.web.dao.DataItemHovDao;
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
public class DataItemHovDaoImpl implements DataItemHovDao {
	
	@Autowired
	DataItemDao dataItemDao;

	@Override
	public PageModel<DataItem> query(Collection<FilterCondition> conditions, Page page) {
		return query(conditions, page, Arrays.asList());
	}

	@Override
	public Class<DataItem> getEntityClass() {
		return DataItem.class;
	}

	@Override
	public PageModel<DataItem> query(Collection<FilterCondition> conditions, Page page, Collection<String> sorts) {
		return dataItemDao.query(conditions, page, sorts);
	}

}
