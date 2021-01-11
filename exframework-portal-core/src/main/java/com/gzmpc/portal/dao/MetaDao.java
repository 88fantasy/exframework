package com.gzmpc.portal.dao;

import java.util.Collection;

import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.Meta;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface MetaDao<T extends Meta> {
	
	Collection<String> allKeys();
	
	Collection<T> all();
	
	T findByKey(String code) throws NotFoundException;
	
	int insert(T entity);
	
	int update(T entity);
	
	int remove(String code);
}
