package com.gzmpc.portal.metadata.queryparam;

import com.gzmpc.portal.metadata.Meta;

/**
 * 查询项
 */
public class QueryParamItem extends Meta {

	private static final long serialVersionUID = 6615001544751262998L;

	/**
	 * 查询类型
	 */
	private String qpiType;

	/**
	 *  参数
	 */
	private String qpiParam;

	/**
	 * 数据库字段
	 */
	private String queryField;

	/**
	 * 初值
	 */
	private String initvalue;

	private String tablename;// 字段所属表名或视图名
	private String ischildrenfield;// 是否是子查询字段
	private String defaultconditionjs;// 默认查询条件的JS
	private String snsretcolname; // SNS返回的字段
	private String dbbean; // 数据库beanid


	public String getQpiType() {
		return qpiType;
	}

	public void setQpiType(String qpiType) {
		this.qpiType = qpiType;
	}

	public String getQpiParam() {
		return qpiParam;
	}

	public void setQpiParam(String qpiParam) {
		this.qpiParam = qpiParam;
	}

	public String getQueryField() {
		return queryField;
	}

	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}

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

	public QueryParamItem() {
	}

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

	public String getDbbean() {
		return dbbean;
	}

	public void setDbbean(String dbbean) {
		this.dbbean = dbbean;
	}

}
