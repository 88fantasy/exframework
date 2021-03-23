package com.gzmpc.support.doc.entity;

/**
* @author rwe
* @version 创建时间：2021年3月10日 下午2:26:37
* 数据列
*/

public class DataBaseTableField {

	private String field;
	
	private String description;
	
	private Class<?> type;
	
	private boolean nullable;
	
	private String defaultValue;

	public DataBaseTableField(String field, String description, Class<?> type, boolean nullable, String defaultValue) {
		this.field = field;
		this.description = description;
		this.type = type;
		this.nullable = nullable;
		this.defaultValue = defaultValue;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public boolean getNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
}
