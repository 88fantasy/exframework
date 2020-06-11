package com.gzmpc.support.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @author rwe
* @version 创建时间：Jun 9, 2020 11:06:42 PM
* 分页信息
*/

@ApiModel(value = "分页信息")
public class Pager extends Page {

	@ApiModelProperty(value = "总数")
    private Long total;
	
	public Pager(Long total, Page page) {
		this.total = total;
		this.setCurrent(page.getCurrent());
		this.setPageSize(page.getPageSize());
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
