package com.gzmpc.metadata.dict;

/**
 *
 * Author: rwe
 * Date: Dec 26, 2020
 *
 * Copyright @ 2020 
 * 字典枚举
 */
public interface DictionaryEnum<T> {
	
	T[] getValues();

	String getKey();

	String getName();
	
//	T enumOf(String key);
}
