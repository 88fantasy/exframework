package com.gzmpc.portal.metadata.queryparamitem;

import com.gzmpc.portal.metadata.FilterCondition.FilterConditionOper;
import com.gzmpc.portal.metadata.queryparam.QueryParamItem;
import com.gzmpc.portal.metadata.sys.Account;

/**
 * 查询类型基类
 * 
 * 返回一个map，包括类型，展现
 */
public abstract class QueryParamBase {

	public final static String QUERYPARAM_DATATYPE_STRING = "string";
	public final static String QUERYPARAM_DATATYPE_NUMBER = "number";
	public final static String QUERYPARAM_DATATYPE_DATE = "date";
	public final static String QUERYPARAM_DATATYPE_DDL = "ddl";
	public final static String QUERYPARAM_DATATYPE_STRINGUPPER = "upper";
	public final static String QUERYPARAM_DATATYPE_STRINGLOWER = "lower";

	/**
	 * 列
	 */
	private String colfield;
	
	/**
	 * 显示名称
	 */
	private String colname;
	
	/**
	 * 数据类型
	 */
	private String dataType;
	
	/**
	 * 可选操作
	 */
	private FilterConditionOper[] operations;
	
	private Object operdata;

	public String getColfield() {
		return colfield;
	}

	public void setColfield(String colfield) {
		this.colfield = colfield;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public FilterConditionOper[] getOperations() {
		return operations;
	}

	public void setOperations(FilterConditionOper[] operations) {
		this.operations = operations;
	}

	public Object getOperdata() {
		return operdata;
	}

	public void setOperdata(Object operdata) {
		this.operdata = operdata;
	}

	public abstract void initBase(Account account, QueryParamItem qpi);

	public void initBaseCommon(Account account, QueryParamItem qpi) {
		this.setColfield(qpi.getQueryField());
		this.setColname(qpi.getName());
		this.setDataType(QUERYPARAM_DATATYPE_STRING);

	}

//	public Oper getDefaultOper() {
//		switch (displayType) {
//		case CHECKBOX:
//			return Oper.EQUAL;
//		case DICTIONARY:
//			return Oper.IN;
//		case INPUT:
//		case PASSWORD:
//		case READONLY:
//			return Oper.MATCHING;
//		default:
//			return Oper.EQUAL;
//		}
//	}
}
