package org.exframework.support.common.util;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
* @author rwe
* @version 创建时间：2017年2月14日 上午11:44:40
* 类说明
*/

@Component
public class MapUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void putIfNotNull(Map map,Object key, Object value) {
		if(value != null) {
			map.put(key, value);
		}
	}
}
