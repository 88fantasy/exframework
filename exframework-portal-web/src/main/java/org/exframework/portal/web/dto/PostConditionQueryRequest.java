package org.exframework.portal.web.dto;

import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询字典请求
 * @author rwe
 * @since Jan 5, 2021
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
	
	@ApiModelProperty(value = "排序信息")
	String[] sorts;
	
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

	public String[] getSorts() {
		return sorts;
	}

	public void setSorts(String[] sorts) {
		this.sorts = sorts;
	}
	
	
}
