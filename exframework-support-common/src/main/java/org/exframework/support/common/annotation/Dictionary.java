package org.exframework.support.common.annotation;

import java.lang.annotation.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ANNOTATION_TYPE})
/**
 * 字典
 * @author rwe
 * @since Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface Dictionary {

	String value() default "";
	
	String name() default "";

	/**
	 * 用户存储本地字典
	 */
	class DictionaryCache {

		private static final ConcurrentHashMap<String, Class<Enum<?>>> cache =  new ConcurrentHashMap<>(8);

		public static void clear() {
			if (cache != null) {
				cache.clear();
			}
		}

		public static void bind(String key, Class<Enum<?>> value) {
			if (key == null) {
				throw new NullPointerException("key cannot be null");
			} else if (value == null) {
				cache.remove(key);
			} else {
				cache.put(key, value);
			}
		}

		public static Class<Enum<?>> getBind(String key) {
			return cache.get(key);
		}

		public static Set<Map.Entry<String, Class<Enum<?>>>> entrySet() {
			return cache.entrySet();
		}
	}
}
