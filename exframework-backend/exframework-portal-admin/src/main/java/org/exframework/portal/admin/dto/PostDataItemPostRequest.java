package org.exframework.portal.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.exframework.portal.metadata.di.DataItem;

import javax.validation.constraints.NotNull;

/**
 * 新增修改字典请求 Author: rwe Date: Jan 9, 2021
 *
 * Copyright @ 2021
 * 
 */
@ApiModel(value = "新增修改数据项请求")
public class PostDataItemPostRequest {

	@ApiModelProperty(value = "数据项")
	@NotNull
	private DataItem item;
	
	@ApiModelProperty(value = "新增或修改")
	private boolean create;

	public PostDataItemPostRequest() {
		this.create = false;
	}
	
	public DataItem getItem() {
		return item;
	}

	public void setItem(DataItem item) {
		this.item = item;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

}
