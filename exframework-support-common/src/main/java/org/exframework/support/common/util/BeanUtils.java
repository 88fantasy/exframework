package org.exframework.support.common.util;


/**
 * 实体工具类
 * Author: rwe
 * Date: Jan 19, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

	public static <T> T copyTo(Object o , Class<T> clazz) {
		T t = BeanUtils.instantiateClass(clazz);
		BeanUtils.copyProperties(o, t);
		return t;
	}
}
