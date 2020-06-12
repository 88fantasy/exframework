package com.gzmpc.support.common.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @author rwe
* @version 创建时间：Jun 9, 2020 10:50:30 PM
* 分页数据对象VO
*/

@ApiModel(value = "分页数据对象")
public class PageModel<T> {

	@ApiModelProperty(value = "分页信息")
	private Pager pager;
	
	@ApiModelProperty(value = "数据")
	private List<T> data;

	public PageModel(Pager pager, List<T> data) {
		this.pager = pager;
		this.data = data;
	}
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
