package com.gzmpc.metadata.enums;

import com.gzmpc.metadata.dict.Dictionary;
import com.gzmpc.metadata.dict.DictionaryEnum;

/**
 *
 * Author: rwe
 * Date: Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Dictionary
public enum AccountStatusTypeEnum implements DictionaryEnum<String> {

	/**
	 * 有效
	 */
	VALID("valid", "有效"),

	/**
	 * 失效
	 */
	INVALID("invalid", "失效"),

	/**
	 * 禁止
	 */
	FORBIDDEN("forbidden", "禁止")

	;

	private String key;

	private String name;

	private AccountStatusTypeEnum(String key, String name) {
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