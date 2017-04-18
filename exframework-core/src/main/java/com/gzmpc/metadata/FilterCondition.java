package com.gzmpc.metadata;

public class FilterCondition {
	private String filterName; // 过滤条件值
	private String filterOpera; // 过滤操作符
	private String filterValue; // 过滤条件值
	private String filterDataType; // 数据类型
	private String filterValue2;// 值2 between clq 20121127

	public static String DATE_DATA_TYPE = "DATE";
	public static String STRING_DATA_TYPE = "STRING";

	public FilterCondition(String filterName, String filterOpera,
			String filterValue) {
		this.filterName = filterName;
		this.filterOpera = filterOpera;
		this.filterValue = filterValue;
		this.filterDataType = STRING_DATA_TYPE; // 默认是字符串类型
	}

	public FilterCondition(String filterName, String filterOpera,
			String filterValue, String filterDataType) {
		this.filterName = filterName;
		this.filterOpera = filterOpera;
		this.filterValue = filterValue;
		this.filterDataType = filterDataType;
	}
	
	public FilterCondition(String filterName, String filterOpera,
			String filterValue,String filterValue2, String filterDataType) {
		this.filterName = filterName;
		this.filterOpera = filterOpera;
		this.filterValue = filterValue;
		this.filterValue2 = filterValue2;
		this.filterDataType = filterDataType;
	}

	public String getFilterDataType() {
		return filterDataType;
	}

	public String getFilterName() {
		return filterName;
	}

	public String getFilterOpera() {
		return filterOpera;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public void setFilterOpera(String filterOpera) {
		this.filterOpera = filterOpera;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public void setFilterDataType(String filterDataType) {
		this.filterDataType = filterDataType;
	}

	public String getFilterValue2() {
		return filterValue2;
	}

	public void setFilterValue2(String filterValue2) {
		this.filterValue2 = filterValue2;
	}

}