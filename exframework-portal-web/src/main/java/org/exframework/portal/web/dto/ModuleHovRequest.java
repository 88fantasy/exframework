package org.exframework.portal.web.dto;

import org.exframework.portal.metadata.di.DataItemRef;
import org.exframework.support.common.entity.Page;

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
