package org.exframework.support.common.util;


import java.util.function.Function;

/**
 * 实体工具类
 * @author rwe
 * @since Jan 19, 2021
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

	public static <T, R> R translateTo(T t, Function<T,R> function) {
		return function.apply(t);
	}
}
