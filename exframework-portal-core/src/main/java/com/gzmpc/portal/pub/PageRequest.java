package com.gzmpc.portal.pub;

import com.gzmpc.support.common.entity.Page;

/**
 * 分页请求
 * Author: rwe
 * Date: Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
public interface PageRequest {
	
	default Page getPage(){
		return Page.DEFAULT;
	}
	
}
