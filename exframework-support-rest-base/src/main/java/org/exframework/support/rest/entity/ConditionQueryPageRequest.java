package org.exframework.support.rest.entity;

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
@ApiModel(value="通用分页查询请求")
public class ConditionQueryPageRequest extends QueryPageRequest {

	@ApiModelProperty(value = "查询条件")
	FilterCondition[] conditions;

	
	public ConditionQueryPageRequest() {
		setPage(Page.DEFAULT);
		this.conditions = new FilterCondition[]{};
	}

	public FilterCondition[] getConditions() {
		return conditions;
	}

	public void setConditions(FilterCondition[] conditions) {
		this.conditions = conditions;
	}


	
}
