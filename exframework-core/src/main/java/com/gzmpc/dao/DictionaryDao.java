package com.gzmpc.dao;

import java.util.Map;

import com.gzmpc.exception.NotFoundException;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface DictionaryDao {

	Map<String,String> findByKey(String dictKey) throws NotFoundException;
	
	String[] allKeys();
	
}
