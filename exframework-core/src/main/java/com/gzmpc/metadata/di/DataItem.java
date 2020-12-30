package com.gzmpc.metadata.di;

import com.gzmpc.metadata.Meta;
import com.gzmpc.metadata.dict.Dictionary;
import com.gzmpc.metadata.dict.DictionaryEnum;

/**
 * 数据项
 */
public class DataItem extends Meta {

	private static final long serialVersionUID = -6304054523810170909L;

	/**
	 * 显示风格
	 */
	@Dictionary(key = "displayType")
	private DataItemDisplayTypeEnum type;

	/**
	 * 风格的关键值（如果是querylist形的，就配置key,如果是sqllist形的就配置sqllist,如果是list形的，就直接配置list列表内容)
	 */
	private String displayKey;

	/**
	 * 校证输入值类型
	 */
	@Dictionary(key = "valueType")
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

	/**
	 * 数据项显示类型
	 * 
	 * @author rwe
	 *
	 */
	public enum DataItemDisplayTypeEnum implements DictionaryEnum<DataItemDisplayTypeEnum> {
		/**
		 * 输入
		 */
		INPUT("input", "输入框"),
		
		/**
		 * 密码输入框
		 */
		PASSWORD("password", "密码输入框"),

		/**
		 * 只读
		 */
		READONLY("readonly", "只读"),

		/**
		 * 是否
		 */
		CHECKBOX("checkbox", "是否"),

		/**
		 * 字典
		 */
		DICTIONARY("dictionary", "字典");

		private String key;

		private String name;

		private DataItemDisplayTypeEnum(String key, String name) {
			this.key = key;
			this.name = name;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public DataItemDisplayTypeEnum[] getValues() {
			return DataItemDisplayTypeEnum.values();
		}

	}

	/**
	 * 验证数据类型
	 * @author rwe
	 *
	 */
	public enum DataItemValueTypeEnum implements DictionaryEnum<DataItemValueTypeEnum> {

		/**
		 * 整数
		 */
		LONG("long", "整数"),

		/**
		 * 字符串
		 */
		STRING("string", "字符串"),
		
		/**
		 * 大写字符串
		 */
		UPPERSTRING("upperstring", "大写字符串"),
		
		/**
		 * 小写字符串
		 */
		LOWERSTRING("lowerstring", "大写字符串"),

		/**
		 * 小数
		 */
		BIGDECIMAL("bigdecimal", "小数"),

		/**
		 * 布尔
		 */
		BOOLEAN("boolean", "布尔"),

		/**
		 * 日期
		 */
		DATE("date", "日期"),

		/**
		 * 日期
		 */
		DATETIME("datetime", "日期时间")

		;

		private String key;

		private String name;

		private DataItemValueTypeEnum(String key, String name) {
			this.key = key;
			this.name = name;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public DataItemValueTypeEnum[] getValues() {
			return DataItemValueTypeEnum.values();
		}
	}
}
