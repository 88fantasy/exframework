package com.gzmpc.portal.metadata.di;

import com.gzmpc.portal.metadata.entity.EntityClass;

/**
 * 扩展数据项
 * Author: rwe
 * Date: Jan 11, 2021
 *
 * Copyright @ 2021 
 * 
 */
@EntityClass
public class DataItemExtend extends DataItem {

	private static final long serialVersionUID = -3668210131610717427L;
	
	public static final String SPLITER = "#";
	/**
	 * 对象码
	 */
	@DataItemEntity(value = "objectCode", name = "对象码")
	private String objectCode;

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	
	boolean isExtend() {
		return true;
	}
}
