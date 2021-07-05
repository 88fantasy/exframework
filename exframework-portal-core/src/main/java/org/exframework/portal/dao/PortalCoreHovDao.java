package org.exframework.portal.dao;

import java.util.Collection;
import java.util.List;

import org.exframework.portal.metadata.hov.Hov;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PortalCoreHovDao extends PortalCoreMetaDao<Hov> {

	PageModel<Hov> query(Collection<FilterCondition> params, Page page);
	
	List<Hov> list(Collection<FilterCondition> params);
}
