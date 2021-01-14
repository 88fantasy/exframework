package com.gzmpc.portal.web.dto;

import com.gzmpc.portal.metadata.di.DataItemRef;
import com.gzmpc.support.common.entity.Page;

/**
 * 模块参照请求
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */

public class ModuleHovRequest extends GlobalPostQueryRequest {

	@DataItemRef("code")
	private String code;
	
	@DataItemRef("name")
	private String name;
	
	private Page page;
	
}
