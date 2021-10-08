package org.exframework.portal.dao;

import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.Meta;

import java.util.Collection;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PortalCoreMetaDao<T extends Meta> {
	
	Collection<String> allKeys();
	
	Collection<T> all();
	
	T findByKey(String code) throws NotFoundException;
	
	int insert(T entity);
	
	int update(T entity);
	
	int remove(String code);
}
