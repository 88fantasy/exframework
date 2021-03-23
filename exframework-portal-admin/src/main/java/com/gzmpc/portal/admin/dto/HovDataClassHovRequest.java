package com.gzmpc.portal.admin.dto;

import com.gzmpc.portal.pub.PageRequest;
import com.gzmpc.support.common.entity.Page;

/**
 *
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */

public class HovDataClassHovRequest implements PageRequest {

//	@DataItemRef("code")
	private String className;
	
//	@DataItemRef("name")
	private String entityClass;
	
	private Page page;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
