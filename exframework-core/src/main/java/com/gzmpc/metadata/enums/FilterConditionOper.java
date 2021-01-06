package com.gzmpc.metadata.enums;

import com.gzmpc.metadata.dict.Dictionary;
import com.gzmpc.metadata.dict.DictionaryEnum;

/**
 * 条件操作符
 * Author: rwe
 * Date: Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Dictionary
public enum FilterConditionOper implements DictionaryEnum<String> {

	EQUAL("equal", "等于"), GREATER("greater", "大于"), LESS("less", "小于"), BETWEEN("between", "介于"),
	GREATER_EQUAL("greater_equal", "大于等于"), LESS_EQUAL("less_equal", "小于等于"), IN("in", "包含"),
	MATCHING("matching", "匹配"), NOT_EQUAL("not_equal", "不等于"), ISNULL("is_null", "为空"),
	IS_NOT_NULL("is_not_null", "不为空"), STR("str", "自定义"),;

	private String key;

	private String name;

	private FilterConditionOper(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return this.key;
	}
}
