package com.gzmpc.metadata.queryparam;


import com.gzmpc.metadata.MDObject;
/**
 * 查询框类
 * */
public class QueryParam extends MDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4865849738453003204L;
	
	private String funccode;
	private QueryParamItem[] queryParamItems;
	
	public String getFunccode() {
		return funccode;
	}
	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}
	public QueryParamItem[] getQueryParamItems() {
		return queryParamItems;
	}
	public void setQueryParamItems(QueryParamItem[] queryParamItems) {
		this.queryParamItems = queryParamItems;
	}

}
