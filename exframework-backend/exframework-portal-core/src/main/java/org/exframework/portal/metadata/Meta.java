package org.exframework.portal.metadata;

import org.exframework.portal.metadata.di.DataItemEntity;
import org.exframework.portal.metadata.entity.EntityClass;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 元数据元素的基类，包含名称信息、版本信息、与显示信息，form,attribute,dataItem等继承这个基类
 */
@EntityClass
public class Meta implements Serializable {
	
	private static final long serialVersionUID = 3327328749207805102L;
	
	/**
	 * 代码
	 */
	@NotEmpty
	@DataItemEntity(value = "code", name = "代码")
	private String code;
	
	/**
	 * 名称
	 */
	@NotEmpty
	@DataItemEntity(value = "name", name = "名称")
	private String name;
	
	/**
	 * 描述
	 */
	@DataItemEntity(value = "description", name = "描述")
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

	@Override
	public String toString() {
		String shortName = getClass().getName();
		shortName = shortName.substring(shortName.lastIndexOf(".") + 1);
		return shortName + ",name:" + getName() + ",key:" + getCode();
	}
}
