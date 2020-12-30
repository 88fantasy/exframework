package com.gzmpc.metadata.hov;
/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */

import com.gzmpc.metadata.Meta;
import com.gzmpc.metadata.queryparam.QueryParamItem;

public class Hov extends Meta {

	private static final long serialVersionUID = 8096784702772680510L;

	private QueryParamItem[] queryParamItems;
	
	private String[] columns;
	
	private String returnKey;

	public QueryParamItem[] getQueryParamItems() {
		return queryParamItems;
	}

	public void setQueryParamItems(QueryParamItem[] queryParamItems) {
		this.queryParamItems = queryParamItems;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public String getReturnKey() {
		return returnKey;
	}

	public void setReturnKey(String returnKey) {
		this.returnKey = returnKey;
	}
	
	
}
