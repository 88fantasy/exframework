package org.exframework.portal.admin.dto;

import javax.validation.constraints.NotNull;

import org.exframework.portal.metadata.dict.DictionaryItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新增修改字典请求 Author: rwe Date: Jan 9, 2021
 *
 * Copyright @ 2021
 * 
 */
@ApiModel(value = "新增修改字典请求")
public class PostDictionaryPostRequest {

	@ApiModelProperty(value = "字典")
	@NotNull
	private DictionaryItem item;
	
	@ApiModelProperty(value = "新增或修改")
	private boolean create;

	public PostDictionaryPostRequest() {
		this.create = false;
	}
	
	public DictionaryItem getItem() {
		return item;
	}

	public void setItem(DictionaryItem item) {
		this.item = item;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

}
