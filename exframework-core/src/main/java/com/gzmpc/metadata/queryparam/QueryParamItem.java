package com.gzmpc.metadata.queryparam;

/**
 * 查询项
 * */
public class QueryParamItem {
	
	private String queryparamcode;//查询编码
	private String qpitype;//查询类型
	private String queryname;//提示
	private String queryoper;//操作 包括 = in exists
	private String queryfield;//数据库字段	
	private String initvalue;//初值
	private String tooltip; //提示
	private String tablename;//字段所属表名或视图名
	private String ischildrenfield;//是否是子查询字段
	private String defaultconditionjs;//默认查询条件的JS
	private String snsretcolname; //SNS返回的字段
	private String dbbean; //数据库beanid
	
	public String getSnsretcolname() {
		return snsretcolname;
	}

	public void setSnsretcolname(String snsretcolname) {
		this.snsretcolname = snsretcolname;
	}

	public String getDefaultconditionjs() {
		return defaultconditionjs;
	}

	public void setDefaultconditionjs(String defaultconditionjs) {
		this.defaultconditionjs = defaultconditionjs;
	}

	public QueryParamItem(){}
	
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getIschildrenfield() {
		return ischildrenfield;
	}

	public void setIschildrenfield(String ischildrenfield) {
		this.ischildrenfield = ischildrenfield;
	}

	public String getInitvalue() {
		return initvalue;
	}

	public void setInitvalue(String initvalue) {
		this.initvalue = initvalue;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getQueryparamcode() {
		return queryparamcode;
	}
	public void setQueryparamcode(String queryparamcode) {
		this.queryparamcode = queryparamcode;
	}
	public String getQpitype() {
		return qpitype;
	}
	public void setQpitype(String qpitype) {
		this.qpitype = qpitype;
	}
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public String getQueryoper() {
		return queryoper;
	}
	public void setQueryoper(String queryoper) {
		this.queryoper = queryoper;
	}
	public String getQueryfield() {
		return queryfield;
	}
	public void setQueryfield(String queryfield) {
		this.queryfield = queryfield;
	}

	public String getDbbean() {
		return dbbean;
	}

	public void setDbbean(String dbbean) {
		this.dbbean = dbbean;
	}

}
