package com.gzmpc.metadata.enums;

import com.gzmpc.metadata.dict.Dictionary;
import com.gzmpc.metadata.dict.DictionaryEnum;

/**
 * 数据项显示类型
 * Author: rwe
 * Date: Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Dictionary
public enum DataItemDisplayTypeEnum implements DictionaryEnum<String> {
	/**
	 * 输入
	 */
	INPUT("input", "输入框"),
	
	/**
	 * 密码输入框
	 */
	PASSWORD("password", "密码输入框"),

	/**
	 * 只读
	 */
	READONLY("readonly", "只读"),

	/**
	 * 是否
	 */
	CHECKBOX("checkbox", "是否"),

	/**
	 * 字典
	 */
	DICTIONARY("dictionary", "字典");

	private String key;

	private String name;

	private DataItemDisplayTypeEnum(String key, String name) {
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
