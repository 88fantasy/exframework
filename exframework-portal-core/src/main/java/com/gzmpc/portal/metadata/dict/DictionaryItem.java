package com.gzmpc.portal.metadata.dict;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.gzmpc.portal.metadata.Meta;

/**
 * 字典项
 * Author: rwe
 * Date: Jan 7, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class DictionaryItem extends Meta {

	private static final long serialVersionUID = 1734744807375718094L;
	
	@NotEmpty
	private Map<String,String> value;

	public Map<String, String> getValue() {
		return value;
	}

	public void setValue(Map<String, String> value) {
		this.value = value;
	}
	
}
