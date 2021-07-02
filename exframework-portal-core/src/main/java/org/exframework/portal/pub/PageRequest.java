package org.exframework.portal.pub;

import org.exframework.support.common.entity.Page;

/**
 * 分页请求
 * @author rwe
 * @since Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
public interface PageRequest {
	
	default Page getPage(){
		return Page.DEFAULT;
	}
	
}
