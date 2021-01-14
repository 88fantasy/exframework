package com.gzmpc.portal.metadata.hov;

/**
 * 参照实体
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */


import com.gzmpc.portal.metadata.Meta;

public class HovBase extends Meta {

	private static final long serialVersionUID = 8096784702772680510L;

	/**
	 * 请求实体类名
	 */
	private String requestClass;
	
	/**
	 * 数据类名
	 */
	private String dataClass;
	
	/**
	 * 返回字段
	 */
	private String returnKey;

	
	public HovBase() {
		super();
	}

	public HovBase(String code, String name, String description, String requestClass, String dataClass, String returnKey) {
		super(code, name, description);
		this.requestClass = requestClass;
		this.dataClass = dataClass;
		this.returnKey = returnKey;
	}
	
	public String getRequestClass() {
		return requestClass;
	}

	public void setRequestClass(String requestClass) {
		this.requestClass = requestClass;
	}

	public String getDataClass() {
		return dataClass;
	}

	public void setDataClass(String dataClass) {
		this.dataClass = dataClass;
	}

	public String getReturnKey() {
		return returnKey;
	}

	public void setReturnKey(String returnKey) {
		this.returnKey = returnKey;
	}
	
	
}
