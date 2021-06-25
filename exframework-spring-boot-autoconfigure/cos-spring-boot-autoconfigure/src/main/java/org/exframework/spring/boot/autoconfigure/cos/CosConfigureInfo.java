package org.exframework.spring.boot.autoconfigure.cos;

import java.io.Serializable;

/**
 *
 * Author: rwe
 * Date: 2021年5月20日
 *
 * Copyright @ 2021 
 * 
 */
public class CosConfigureInfo implements Serializable {

	private static final long serialVersionUID = 1295557445592963483L;
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
