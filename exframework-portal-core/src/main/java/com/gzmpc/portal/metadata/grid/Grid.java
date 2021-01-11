package com.gzmpc.portal.metadata.grid;

import com.gzmpc.portal.metadata.Meta;

public class Grid extends Meta {

	private static final long serialVersionUID = -3738140236694706028L;

	String moduleKey;
	
	String dataSource;
	
	String dataIndex;
	
	String queryType;
	
	String autoQuery;
	
	String pagesize;
	
	String querymoduleid;
	
	String downloadmoduleid;

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getAutoQuery() {
		return autoQuery;
	}

	public void setAutoQuery(String autoQuery) {
		this.autoQuery = autoQuery;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getQuerymoduleid() {
		return querymoduleid;
	}

	public void setQuerymoduleid(String querymoduleid) {
		this.querymoduleid = querymoduleid;
	}

	public String getDownloadmoduleid() {
		return downloadmoduleid;
	}

	public void setDownloadmoduleid(String downloadmoduleid) {
		this.downloadmoduleid = downloadmoduleid;
	}
	
//	String sumfieldnames;
//	int selectmodel;
//	String defalutToolbar;
//	String extendBeanName;
//	String orderby;
//
//	String dbbeanname;// 查询数据时调用的数据库名称
//	boolean needpagecount; // 查询数据时是否提供总页数
//	String afterQueryBeanName;// 查询后处理类

	
}