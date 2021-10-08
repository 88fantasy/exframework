package org.exframework.portal.metadata;

import org.exframework.support.common.entity.Page;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public class QueryRequest {

	/**
	 * 查询条件
	 */
	private FilterConditionEnum[] data;
	
	/**
	 * 分页信息
	 */
	private Page page;

	public FilterConditionEnum[] getData() {
		return data;
	}

	public void setData(FilterConditionEnum[] data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
