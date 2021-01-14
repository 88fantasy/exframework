package com.gzmpc.portal.metadata.hov;

import java.util.Collection;

import com.gzmpc.portal.metadata.grid.Column;

/**
 *
 * Author: rwe
 * Date: Jan 14, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class Hov {

	private Collection<HovQueryParams> queryParams;
	
	private Collection<Column> columns;
	
	private String returnKey;
	
	public Collection<HovQueryParams> getQueryParams() {
		return queryParams;
	}



	public void setQueryParams(Collection<HovQueryParams> queryParams) {
		this.queryParams = queryParams;
	}



	public Collection<Column> getColumns() {
		return columns;
	}



	public void setColumns(Collection<Column> columns) {
		this.columns = columns;
	}



	public String getReturnKey() {
		return returnKey;
	}



	public void setReturnKey(String returnKey) {
		this.returnKey = returnKey;
	}

}
