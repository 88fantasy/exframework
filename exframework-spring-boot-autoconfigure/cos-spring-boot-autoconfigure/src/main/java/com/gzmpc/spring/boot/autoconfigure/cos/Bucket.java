package com.gzmpc.spring.boot.autoconfigure.cos;
/**
 *
 * Author: rwe
 * Date: 2021年5月25日
 *
 * Copyright @ 2021 
 * 
 */
public class Bucket {
	
	private String name;
	
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
