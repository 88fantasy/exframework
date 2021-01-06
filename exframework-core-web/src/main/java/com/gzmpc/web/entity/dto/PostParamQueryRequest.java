package com.gzmpc.web.entity.dto;

import com.gzmpc.support.common.entity.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * Author: rwe
 * Date: Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value="查询参数请求")
public class PostParamQueryRequest {

	@ApiModelProperty(value = "参数编码")
	String code;
	
	@ApiModelProperty(value = "参数帐号")
	String account;
	
	@ApiModelProperty(value = "参数名称")
	String name;
	
	@ApiModelProperty(value = "分页信息")
	Page page;
	
	public PostParamQueryRequest() {
		this.page = Page.DEFAULT;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
