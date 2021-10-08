package org.exframework.portal.web.jdbc.dao;

import org.exframework.portal.dao.PortalCoreDataItemDao;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.web.dao.DataItemHovDao;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author rwe
 * @since Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Repository
public class DataItemHovDaoImpl implements DataItemHovDao {
	
	@Autowired
	PortalCoreDataItemDao portalCoreDataItemDao;

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
		return portalCoreDataItemDao.query(conditions, page, sorts);
	}

}
