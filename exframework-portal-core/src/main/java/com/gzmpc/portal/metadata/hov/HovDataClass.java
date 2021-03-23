package com.gzmpc.portal.metadata.hov;
/**
 *	参照实现类
 * Author: rwe
 * Date: 2021年3月19日
 *
 * Copyright @ 2021 
 * 
 */
public class HovDataClass {
	
	private String entityClass;
	
	private String className;
	
	public HovDataClass(String entityClass, String className) {
		super();
		this.entityClass = entityClass;
		this.className = className;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
