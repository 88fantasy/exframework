package com.gzmpc.metadata.form;

import com.gzmpc.metadata.Meta;
import com.gzmpc.metadata.attribute.Attribute;

/**
 * 定义了form,相当于field的集合
 * 
 * @author rwe
 * @version 1.0
 */

public class Form extends Meta {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7713481434157045007L;
	
	private boolean isvaliddata;// 是否需要校验。主要是为了生成的校验文件尽可能少些
	private Attribute[] attributes;

	public Attribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}
	
	public boolean getIsvaliddata() {
		return isvaliddata;
	}

	public void setIsvaliddata(boolean isvaliddata) {
		this.isvaliddata = isvaliddata;
	}
}
