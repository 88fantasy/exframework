package com.gzmpc.metadata;

import com.gzmpc.support.common.entity.Page;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public class QueryRequest {

	/**
	 * 查询条件
	 */
	private FilterCondition[] data;
	
	/**
	 * 分页信息
	 */
	private Page page;

	public FilterCondition[] getData() {
		return data;
	}

	public void setData(FilterCondition[] data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
