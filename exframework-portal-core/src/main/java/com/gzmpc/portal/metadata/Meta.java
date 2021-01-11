package com.gzmpc.portal.metadata;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.gzmpc.portal.metadata.di.DataItemField;

/**
 * 元数据元素的基类，包含名称信息、版本信息、与显示信息，form,attribute,dataItem等继承这个基类
 */
public class Meta implements Serializable {
	
	private static final long serialVersionUID = 3327328749207805102L;
	
	/**
	 * 代码
	 */
	@NotEmpty
	@DataItemField(value = "code", name = "代码")
	private String code;
	
	/**
	 * 名称
	 */
	@NotEmpty
	@DataItemField(value = "name", name = "名称")
	private String name;
	
	/**
	 * 描述
	 */
	@DataItemField(value = "description", name = "描述")
	private String  description;

	public Meta() {
		super();
	}

	public Meta(@NotEmpty String code, @NotEmpty String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toString() {
		String shortName = getClass().getName();
		shortName = shortName.substring(shortName.lastIndexOf(".") + 1);
		return shortName + ",name:" + getName() + ",key:" + getCode();
	}
}
