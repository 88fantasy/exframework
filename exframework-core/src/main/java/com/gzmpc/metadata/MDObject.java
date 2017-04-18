package com.gzmpc.metadata;

import java.io.Serializable;

/**
 * 元数据元素的基类，包含名称信息、版本信息、与显示信息，form,attribute,dataItem等继承这个基类
 */
public class MDObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7028026439648591802L;
	
	private String code;// 代码
	private String name;// 名称
	private String comment;// 备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String toString() {
		String shortName = getClass().getName();
		shortName = shortName.substring(shortName.lastIndexOf(".") + 1);
		return shortName + ",name:" + getName() + ",code:" + getCode();
	}
}
