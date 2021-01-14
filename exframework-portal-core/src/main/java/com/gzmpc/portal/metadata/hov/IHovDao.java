package com.gzmpc.portal.metadata.hov;

import java.util.Collection;

import com.gzmpc.portal.metadata.FilterCondition;
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
public interface IHovDao<T> {
	
	Class<T> getEntityClass();

	PageModel<T> query(Collection<FilterCondition> conditions, Page page);
	
}
