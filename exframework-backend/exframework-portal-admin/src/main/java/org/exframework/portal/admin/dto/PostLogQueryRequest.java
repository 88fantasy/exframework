package org.exframework.portal.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;

/**
 * 查询字典请求
 * @author rwe
 * @since Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value="查询日志请求")
public class PostLogQueryRequest {

	@ApiModelProperty(value = "查询条件")
	FilterCondition[] conditions;
	
	@ApiModelProperty(value = "分页信息")
	Page page;
	
	public PostLogQueryRequest() {
		this.page = Page.DEFAULT;
	}

	public FilterCondition[] getConditions() {
		return conditions;
	}

	public void setConditions(FilterCondition[] conditions) {
		this.conditions = conditions;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
