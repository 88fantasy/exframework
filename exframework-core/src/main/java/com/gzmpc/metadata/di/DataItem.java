package com.gzmpc.metadata.di;

import com.gzmpc.metadata.Meta;
import com.gzmpc.metadata.enums.DataItemDisplayTypeEnum;
import com.gzmpc.metadata.enums.DataItemValueTypeEnum;

/**
 * 数据项
 */
public class DataItem extends Meta {

	private static final long serialVersionUID = -6304054523810170909L;

	/**
	 * 显示风格
	 */
	private DataItemDisplayTypeEnum type;

	/**
	 * 风格的关键值（如果是querylist形的，就配置key,如果是sqllist形的就配置sqllist,如果是list形的，就直接配置list列表内容)
	 */
	private String displayKey;

	/**
	 * 校证输入值类型
	 */
	private DataItemValueTypeEnum valueType;

	/**
	 * 长度
	 */
	private int maxlength;

	/**
	 * 精度
	 */
	private int precision;



	public DataItemDisplayTypeEnum getType() {
		return type;
	}

	public void setType(DataItemDisplayTypeEnum type) {
		this.type = type;
	}

	public DataItemValueTypeEnum getValueType() {
		return valueType;
	}

	public void setValueType(DataItemValueTypeEnum valueType) {
		this.valueType = valueType;
	}

	public String getDisplayKey() {
		return displayKey;
	}

	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}


	public int getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	
}
