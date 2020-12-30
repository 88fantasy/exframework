package com.gzmpc.metadata;

import java.io.Serializable;

/**
 * 元数据元素的基类，包含名称信息、版本信息、与显示信息，form,attribute,dataItem等继承这个基类
 */
public class Meta implements Serializable {
	
	private static final long serialVersionUID = 3327328749207805102L;
	
	private String key;// 代码
	private String name;// 名称
	private String comment;// 备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String toString() {
		String shortName = getClass().getName();
		shortName = shortName.substring(shortName.lastIndexOf(".") + 1);
		return shortName + ",name:" + getName() + ",key:" + getKey();
	}
}
