package com.gzmpc.metadata.enums;

import com.gzmpc.metadata.dict.Dictionary;
import com.gzmpc.metadata.dict.DictionaryEnum;

/**
 * 条件操作符 Author: rwe Date: Jan 2, 2021
 *
 * Copyright @ 2021
 * 
 */
@Dictionary
public enum FilterConditionDataType implements DictionaryEnum<String> {

	STRING("string", "字符串"), 
	LIST("list", "数组"), 
	NUMBER("number", "数字"),
	BOOLEAN("boolean", "布尔"), 
	JSON("json", "JSON"), 
	DATE("date", "日期"),
	DATETIME("datetime", "日期时间")
	;

	private String key;

	private String name;

	private FilterConditionDataType(String key, String name) {
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
