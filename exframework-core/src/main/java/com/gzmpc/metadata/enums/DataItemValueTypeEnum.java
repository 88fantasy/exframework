package com.gzmpc.metadata.enums;

import com.gzmpc.metadata.dict.Dictionary;
import com.gzmpc.metadata.dict.DictionaryEnum;

/**
 * 验证数据类型
 * Author: rwe
 * Date: Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Dictionary
public enum DataItemValueTypeEnum implements DictionaryEnum<String> {

	/**
	 * 整数
	 */
	LONG("long", "整数"),

	/**
	 * 字符串
	 */
	STRING("string", "字符串"),
	
	/**
	 * 大写字符串
	 */
	UPPERSTRING("upperstring", "大写字符串"),
	
	/**
	 * 小写字符串
	 */
	LOWERSTRING("lowerstring", "大写字符串"),

	/**
	 * 小数
	 */
	BIGDECIMAL("bigdecimal", "小数"),

	/**
	 * 布尔
	 */
	BOOLEAN("boolean", "布尔"),

	/**
	 * 日期
	 */
	DATE("date", "日期"),

	/**
	 * 日期
	 */
	DATETIME("datetime", "日期时间")

	;

	private String key;

	private String name;

	private DataItemValueTypeEnum(String key, String name) {
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
