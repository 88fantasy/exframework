package com.gzmpc.metadata.grid;

public class Grid {
	String toolbarCode;
	String funcCode;
	String gridCode;
	String gridname;
	String dataSource;
	String dataIndex;
	String queryType;
	String autoQuery;
	String pagesize;
	String querymoduleid;
	String downloadmoduleid;
	String sumfieldnames;
	int selectmodel;
	String defalutToolbar;
	String extendBeanName;
	String orderby;

	String dbbeanname;// 查询数据时调用的数据库名称
	boolean needpagecount; // 查询数据时是否提供总页数
	String afterQueryBeanName;// 查询后处理类

	public String getToolbarCode() {
		return toolbarCode;
	}

	public void setToolbarCode(String toolbarCode) {
		this.toolbarCode = toolbarCode;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getGridCode() {
		return gridCode;
	}

	public void setGridCode(String gridCode) {
		this.gridCode = gridCode;
	}

	public String getGridname() {
		return gridname;
	}

	public void setGridname(String gridname) {
		this.gridname = gridname;
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

	public String getSumfieldnames() {
		return sumfieldnames;
	}

	public void setSumfieldnames(String sumfieldnames) {
		this.sumfieldnames = sumfieldnames;
	}

	public int getSelectmodel() {
		return selectmodel;
	}

	public void setSelectmodel(int selectmodel) {
		this.selectmodel = selectmodel;
	}

	public String getDefalutToolbar() {
		return defalutToolbar;
	}

	public void setDefalutToolbar(String defalutToolbar) {
		this.defalutToolbar = defalutToolbar;
	}

	public String getExtendBeanName() {
		return extendBeanName;
	}

	public void setExtendBeanName(String extendBeanName) {
		this.extendBeanName = extendBeanName;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getDbbeanname() {
		return dbbeanname;
	}

	public void setDbbeanname(String dbbeanname) {
		this.dbbeanname = dbbeanname;
	}

	public boolean getNeedpagecount() {
		return needpagecount;
	}

	public void setNeedpagecount(boolean needpagecount) {
		this.needpagecount = needpagecount;
	}

	public String getAfterQueryBeanName() {
		return afterQueryBeanName;
	}

	public void setAfterQueryBeanName(String afterQueryBeanName) {
		this.afterQueryBeanName = afterQueryBeanName;
	}

}