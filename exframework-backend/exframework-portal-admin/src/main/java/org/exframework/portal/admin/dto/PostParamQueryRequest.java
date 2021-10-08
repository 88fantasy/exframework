package org.exframework.portal.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.exframework.portal.web.dto.GlobalPostQueryRequest;

/**
 *
 * @author rwe
 * @since Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value="查询参数请求")
public class PostParamQueryRequest extends GlobalPostQueryRequest {
	
	@ApiModelProperty(value = "参数帐号")
	String account;
	
	public PostParamQueryRequest() {
		super();
	}	
	
}
