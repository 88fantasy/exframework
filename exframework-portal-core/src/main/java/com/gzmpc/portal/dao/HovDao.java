package com.gzmpc.portal.dao;

import java.util.Collection;
import java.util.List;

import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.support.common.entity.FilterCondition;
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
public interface HovDao extends MetaDao<Hov> {

	PageModel<Hov> query(Collection<FilterCondition> params, Page page);
	
	List<Hov> list(Collection<FilterCondition> params);
}
