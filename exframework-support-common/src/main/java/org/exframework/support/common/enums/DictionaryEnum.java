package org.exframework.support.common.enums;

/**
 * @author rwe
 * @since Dec 26, 2020
 * <p>
 * Copyright @ 2020
 * 字典枚举
 */
public interface DictionaryEnum {

    /**
     * 获取代码
     *
     * @return 字典代码
     */
    String name();

    /**
     * 获取描述
     *
     * @return 字典描述
     */
    String getLabel();

	/**
	 * 排序
	 * @return 排序
	 */
	default int getOrder() {
        return 1;
    }
}
