package com.gzmpc.portal.web.dto;

import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.support.common.entity.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询字典请求
 * Author: rwe
 * Date: Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value="通用查询请求")
public class PostConditionQueryRequest {

	@ApiModelProperty(value = "查询条件")
	FilterCondition[] conditions;
	
	@ApiModelProperty(value = "分页信息")
	Page page;
	
	public PostConditionQueryRequest() {
		this.page = Page.DEFAULT;
		this.conditions = new FilterCondition[]{};
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
