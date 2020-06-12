package com.gzmpc.support.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @author rwe
* @version 创建时间：Jun 10, 2020 12:53:48 PM
* 类说明
*/

@ApiModel(value = "页信息")
public class Page {

	@ApiModelProperty(value = "当前页")
    private Integer current = 1;

	@ApiModelProperty(value = "每页条数")
    private Integer pageSize = 20;

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer skip() {
		return (this.getCurrent() - 1 ) * this.getPageSize();
	}
}
