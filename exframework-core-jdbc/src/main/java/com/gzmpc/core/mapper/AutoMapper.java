package com.gzmpc.core.mapper;

import org.springframework.beans.BeanUtils;

import com.gzmpc.exception.InitException;

/**
 *
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface AutoMapper<T> {

	default T mapper(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
			BeanUtils.copyProperties(this, t);
		} catch (Exception e) {
			throw new InitException();
		}
		return t;
	}
}
