package com.gzmpc.metadata.queryparamitem;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.utils.Const;

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
	

	public final static Map<String,Map<String,String>> OPERATION_CONST = new Hashtable<String,Map<String,String>>();
	
	static {
		//这里可多语言化
		
		Map<String,String> OPERATION_EQUAL = new Hashtable<String,String>();
		OPERATION_EQUAL.put("oper", Const.OPER_EQUAL);
		OPERATION_EQUAL.put("zh_cn", "等于");
		OPERATION_CONST.put(Const.OPER_EQUAL, OPERATION_EQUAL);
		
		Map<String,String> OPERATION_GREATER = new Hashtable<String,String>();
		OPERATION_GREATER.put("oper", Const.OPER_GREATER);
		OPERATION_GREATER.put("zh_cn", "大于");
		OPERATION_CONST.put(Const.OPER_GREATER, OPERATION_GREATER);
		
		Map<String,String> OPERATION_GREATER_EQUAL = new Hashtable<String,String>();
		OPERATION_GREATER_EQUAL.put("oper", Const.OPER_GREATER_EQUAL);
		OPERATION_GREATER_EQUAL.put("zh_cn", "大于等于");
		OPERATION_CONST.put(Const.OPER_GREATER_EQUAL, OPERATION_GREATER_EQUAL);
		
		Map<String,String> OPERATION_BETWEEN = new Hashtable<String,String>();
		OPERATION_BETWEEN.put("oper", Const.OPER_BETWEEN);
		OPERATION_BETWEEN.put("zh_cn", "介于");
		OPERATION_CONST.put(Const.OPER_BETWEEN, OPERATION_BETWEEN);
		
		Map<String,String> OPERATION_IN = new Hashtable<String,String>();
		OPERATION_IN.put("oper", Const.OPER_IN);
		OPERATION_IN.put("zh_cn", "包含");
		OPERATION_CONST.put(Const.OPER_IN, OPERATION_IN);
		
		Map<String,String> OPERATION_ISNULL = new Hashtable<String,String>();
		OPERATION_ISNULL.put("oper", Const.OPER_ISNULL);
		OPERATION_ISNULL.put("zh_cn", "为空");
		OPERATION_CONST.put(Const.OPER_ISNULL, OPERATION_ISNULL);
		
		Map<String,String> OPERATION_ISNOTNULL = new Hashtable<String,String>();
		OPERATION_ISNOTNULL.put("oper", Const.OPER_IS_NOT_NULL);
		OPERATION_ISNOTNULL.put("zh_cn", "非空");
		OPERATION_CONST.put(Const.OPER_IS_NOT_NULL, OPERATION_ISNOTNULL);
		
		Map<String,String> OPERATION_LESS = new Hashtable<String,String>();
		OPERATION_LESS.put("oper", Const.OPER_LESS);
		OPERATION_LESS.put("zh_cn", "小于");
		OPERATION_CONST.put(Const.OPER_LESS, OPERATION_LESS);
		
		Map<String,String> OPERATION_LESS_EQUAL = new Hashtable<String,String>();
		OPERATION_LESS_EQUAL.put("oper", Const.OPER_LESS_EQUAL);
		OPERATION_LESS_EQUAL.put("zh_cn", "小于等于");
		OPERATION_CONST.put(Const.OPER_LESS_EQUAL, OPERATION_LESS_EQUAL);
		
		Map<String,String> OPERATION_MATCHING = new Hashtable<String,String>();
		OPERATION_MATCHING.put("oper", Const.OPER_MATCHING);
		OPERATION_MATCHING.put("zh_cn", "匹配");
		OPERATION_CONST.put(Const.OPER_MATCHING, OPERATION_MATCHING);
		
		Map<String,String> OPERATION_NOT_EQUAL = new Hashtable<String,String>();
		OPERATION_NOT_EQUAL.put("oper", Const.OPER_NOT_EQUAL);
		OPERATION_NOT_EQUAL.put("zh_cn", "不等于");
		OPERATION_CONST.put(Const.OPER_NOT_EQUAL, OPERATION_NOT_EQUAL);
		
		Map<String,String> OPERATION_STR = new Hashtable<String,String>();
		OPERATION_STR.put("oper", Const.OPER_STR);
		OPERATION_STR.put("zh_cn", "自定义");
		OPERATION_CONST.put(Const.OPER_STR, OPERATION_STR);
	}
	
	private String colfield;
	private String colname;
	private String datatype;
	private String oper;
	private List<Map<String,String>> operations;
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

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List<Map<String, String>> getOperations() {
		return operations;
	}

	public void setOperations(List<Map<String, String>> operations) {
		this.operations = operations;
	}

	public Object getOperdata() {
		return operdata;
	}

	public void setOperdata(Object operdata) {
		this.operdata = operdata;
	}

	public abstract void initBase(Account account, QueryParamItem qpi) ;
	
	public void initBaseCommon(Account account,QueryParamItem qpi) {
		this.setColfield(qpi.getQueryfield());
		this.setColname(qpi.getQueryname()); //提示
		this.setDatatype(QUERYPARAM_DATATYPE_STRING);
		this.setOper(qpi.getQueryoper());//默认操作符
		
	}
}
