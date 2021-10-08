package org.exframework.portal.admin.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.exframework.portal.admin.dto.PostLogQueryRequest;
import org.exframework.portal.dao.PortalCoreLogDao;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.portal.metadata.sys.Logger;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.rest.entity.ApiResponsePage;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class AdminLogService {

	@Autowired
	PortalCoreLogDao portalCoreLogDao;
	
	public ApiResponsePage<Logger> query(PostLogQueryRequest request) {
		Collection<FilterCondition> params = request.getConditions() == null? Collections.emptyList() : Arrays.asList(request.getConditions());
		PageModel<Logger> model = portalCoreLogDao.query(params, request.getPage());
		return new ApiResponsePage<Logger>(model);
	}
}
