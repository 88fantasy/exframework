package org.exframework.portal.metadata.di;

import org.exframework.portal.metadata.entity.EntityClass;

/**
 * 扩展数据项
 * @author rwe
 * @since Jan 11, 2021
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
	
	@Override
	boolean isExtend() {
		return true;
	}
}
