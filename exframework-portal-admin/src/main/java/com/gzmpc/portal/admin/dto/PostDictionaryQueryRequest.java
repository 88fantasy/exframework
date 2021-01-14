package com.gzmpc.portal.admin.dto;

import com.gzmpc.portal.web.dto.GlobalPostQueryRequest;

import io.swagger.annotations.ApiModel;

/**
 * 查询字典请求
 * Author: rwe
 * Date: Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value="查询字典请求")
public class PostDictionaryQueryRequest extends GlobalPostQueryRequest {

	public PostDictionaryQueryRequest() {
		super();
	}
}
