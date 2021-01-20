package com.gzmpc.portal.metadata.di;

import com.gzmpc.portal.metadata.Meta;
import com.gzmpc.portal.metadata.dict.Dictionary;
import com.gzmpc.portal.metadata.dict.DictionaryEnum;
import com.gzmpc.portal.metadata.dict.DictionaryEnumClass;
import com.gzmpc.portal.metadata.entity.EntityClass;

/**
 * 数据项
 */
@EntityClass
public class DataItem extends Meta implements DictionaryEnumClass {

	private static final long serialVersionUID = -6304054523810170909L;

	/**
	 * 显示风格
	 */
	@DataItemEntity(value = "DataItemDisplayType", name = "显示风格", type = DataItemDisplayTypeEnum.DICTIONARY, displayKey = "DataItemDisplayType")
	private DataItemDisplayTypeEnum type;

	/**
	 * 风格的关键值（如果是querylist形的，就配置key,如果是sqllist形的就配置sqllist,如果是list形的，就直接配置list列表内容)
	 */
	@DataItemEntity(value = "DataItemDisplayType", name = "风格的关键值")
	private String displayKey;

	/**
	 * 校证输入值类型
	 */
	@DataItemEntity(value = "DataItemValueType", name = "校证输入值类型", type = DataItemDisplayTypeEnum.DICTIONARY, displayKey = "DataItemValueType")
	private DataItemValueTypeEnum valueType;

	/**
	 * 长度
	 */
	@DataItemEntity(value = "maxlength", name = "长度")
	private int maxlength;

	/**
	 * 精度
	 */
	@DataItemEntity(value = "digits", name = "精度")
	private int digits;

	public DataItem() {
		super();
	}

	public DataItem(String code, String name, String description, DataItemDisplayTypeEnum type, String displayKey, DataItemValueTypeEnum valueType, int maxlength,
			int digits) {
		super(code, name, description);
		this.type = type;
		this.displayKey = displayKey;
		this.valueType = valueType;
		this.maxlength = maxlength;
		this.digits = digits;
	}

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

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}
	
	@Dictionary( value = "DataItemDisplayType", name = "数据项显示风格")
	public enum DataItemDisplayTypeEnum implements DictionaryEnum<String> {
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

		private DataItemDisplayTypeEnum() {

		}
		
		private DataItemDisplayTypeEnum(String key, String name) {
			this.key = key;
			this.name = name;
		}
		
		public String getKey() {
			return key;
		}

		public String getName() {
			return name;
		}

		@Override
		public String getValue() {
			return this.key;
		}

	}

	@Dictionary( value = "DataItemValueType", name = "验证数据类型")
	public enum DataItemValueTypeEnum implements DictionaryEnum<String> {
		
		/**
		 * 默认 按 com.gzmpc.portal.dao.DataItemDao.defaultValueType
		 */
		DEFAULT("default", "默认"),

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

		public String getKey() {
			return key;
		}

		public String getName() {
			return name;
		}

		@Override
		public String getValue() {
			return this.key;
		}
	}

	@Override
	public Class<? extends DictionaryEnum<?>>[] enums() {
		return new Class[] {DataItemDisplayTypeEnum.class, DataItemValueTypeEnum.class} ;
	}

	boolean isExtend() {
		return false;
	}
}