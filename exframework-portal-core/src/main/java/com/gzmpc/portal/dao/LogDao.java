package com.gzmpc.portal.dao;

import java.util.Collection;
import java.util.List;

import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.sys.Logger;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;

/**
 *
 * Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
public interface LogDao {

	Logger findByKey(Long loggerId) throws NotFoundException;

	int insert(Logger entity);

	PageModel<Logger> query(Collection<FilterCondition> params, Page page);
	
	List<Logger> list(Collection<FilterCondition> params);
}
