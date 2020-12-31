package com.gzmpc.metadata.queryparam;

import com.gzmpc.metadata.Meta;

/**
 * 查询框类
 */
public class QueryParam extends Meta {

	private static final long serialVersionUID = -4865849738453003204L;

	private String moduleKey;

	private QueryParamItem[] queryParamItems;

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	public QueryParamItem[] getQueryParamItems() {
		return queryParamItems;
	}

	public void setQueryParamItems(QueryParamItem[] queryParamItems) {
		this.queryParamItems = queryParamItems;
	}
}
