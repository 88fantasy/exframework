package org.exframework.portal.dao;

import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.sys.Logger;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
public interface PortalCoreLogDao {

	Logger findByKey(Long loggerId) throws NotFoundException;

	int insert(Logger entity);

	PageModel<Logger> query(Collection<FilterCondition> params, Page page);
	
	List<Logger> list(Collection<FilterCondition> params);
}
