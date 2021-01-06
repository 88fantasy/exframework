package com.gzmpc.dao;

import java.util.Collection;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.Meta;

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
