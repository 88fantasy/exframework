package org.exframework.portal.web.dto;

import org.exframework.portal.pub.PageRequest;
import org.exframework.support.common.entity.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用请求
 * @author rwe
 * @since Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value="通用请求")
public class GlobalPostQueryRequest implements PageRequest {

	@ApiModelProperty(value = "编码")
	String code;
	
	@ApiModelProperty(value = "名称")
	String name;
	
	@ApiModelProperty(value = "分页信息")
	Page page;
	
	public GlobalPostQueryRequest() {
		this.page = Page.DEFAULT;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
