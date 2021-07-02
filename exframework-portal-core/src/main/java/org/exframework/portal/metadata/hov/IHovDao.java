package org.exframework.portal.metadata.hov;

import java.util.Collection;

import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

/**
 *
 * @author rwe
 * @since Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */
public interface IHovDao<T> {
	
	Class<T> getEntityClass();

	PageModel<T> query(Collection<FilterCondition> conditions, Page page);
	
	PageModel<T> query(Collection<FilterCondition> conditions, Page page, Collection<String> sorts);
	
}
