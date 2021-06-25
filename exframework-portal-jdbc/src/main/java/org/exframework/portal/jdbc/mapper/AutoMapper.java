package org.exframework.portal.jdbc.mapper;

import org.springframework.beans.BeanUtils;

import org.exframework.portal.exception.InitException;

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
