package org.exframework.portal.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模块信息 VO
 * @author rwe
 * @since Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */

@ApiModel(value = "模块信息 VO")
public class ModuleReponse extends MetaResponse {
	
	@ApiModelProperty(value="点赞")
	private long star;

	public long getStar() {
		return star;
	}

	public void setStar(long star) {
		this.star = star;
	}
	
	
}
