package org.exframework.support.common.enums;

/**
 *
 * @author rwe
 * @since Dec 26, 2020
 *
 * Copyright @ 2020 
 * 字典枚举
 */
public interface DictionaryEnum {

	/**
	 * 获取代码
	 * @return 字典代码
	 */
	String name();

	/**
	 * 获取描述
	 * @return 字典描述
	 */
	String getLabel();
}
