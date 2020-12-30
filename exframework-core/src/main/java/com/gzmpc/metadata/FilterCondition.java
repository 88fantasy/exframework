package com.gzmpc.metadata;

import com.gzmpc.metadata.dict.DictionaryEnum;

public class FilterCondition {

	/**
	 * 字段
	 */
	private String key;

	/**
	 * 操作符
	 */
	private Oper oper;

	/**
	 * h 条件值
	 */
	private Object filterValue;

	/**
	 * 数据类型
	 */
	private String filterDataType;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Oper getOper() {
		return oper;
	}

	public void setOper(Oper oper) {
		this.oper = oper;
	}

	public Object getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(Object filterValue) {
		this.filterValue = filterValue;
	}

	public String getFilterDataType() {
		return filterDataType;
	}

	public void setFilterDataType(String filterDataType) {
		this.filterDataType = filterDataType;
	}

	public enum Oper implements DictionaryEnum<Oper> {

		EQUAL("equal", "等于"), GREATER("greater", "大于"), LESS("less", "小于"), BETWEEN("between", "介于"),
		GREATER_EQUAL("greater_equal", "大于等于"), LESS_EQUAL("less_equal", "小于等于"), IN("in", "包含"),
		MATCHING("matching", "匹配"), NOT_EQUAL("not_equal", "不等于"), ISNULL("is_null", "为空"),
		IS_NOT_NULL("is_not_null", "不为空"), STR("str", "自定义"),;

		private String key;

		private String name;

		private Oper(String key, String name) {
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
		public Oper[] getValues() {
			return Oper.values();
		}
	}
}