package com.gzmpc.portal.metadata.query;

import com.gzmpc.portal.metadata.Meta;

/**
 *
 * <p>
 * Title: 自定义查询语句，然后在界面上显示出来
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author rwe
 * @version 1.0
 */

public class QueryDef extends Meta {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4965452746665877120L;

	private String sqldef; // 定义的查询语句
	private String dbname; 
	private String querymoduleid;

	public String getQuerymoduleid() {
		return querymoduleid;
	}

	public void setQuerymoduleid(String querymoduleid) {
		this.querymoduleid = querymoduleid;
	}

	public String getSqldef() {
		return sqldef;
	}

	public void setSqldef(String sqldef) {
		this.sqldef = sqldef;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}


}