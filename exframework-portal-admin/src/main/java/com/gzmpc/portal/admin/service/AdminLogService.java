package com.gzmpc.portal.admin.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.admin.dto.PostLogQueryRequest;
import com.gzmpc.portal.dao.LogDao;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.sys.Logger;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.rest.entity.ApiResponsePage;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class AdminLogService {

	@Autowired
	LogDao logDao;
	
	public ApiResponsePage<Logger> query(PostLogQueryRequest request) {
		Collection<FilterCondition> params = request.getConditions() == null? Collections.emptyList() : Arrays.asList(request.getConditions());
		PageModel<Logger> model = logDao.query(params, request.getPage());
		return new ApiResponsePage<Logger>(model);
	}
}
