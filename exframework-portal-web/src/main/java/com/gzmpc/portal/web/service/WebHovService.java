package com.gzmpc.portal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.service.sys.HovService;
import com.gzmpc.support.rest.entity.ApiResponsePage;
import com.gzmpc.support.rest.enums.ResultCode;

/**
 *
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Service
public class WebHovService {

	@Autowired
	HovService hovService;
	

	public <T, U> ApiResponsePage<T> query(String code, Object request, Class<U> clazz) {
		Hov hov = hovService.findByKey(code);
		if(hov != null) {
			return null;
		}
		else {
			return ApiResponsePage.<T>notFound("找不到此hov") ;
		}
	}
}
