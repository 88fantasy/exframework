package org.exframework.portal.admin.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.exframework.portal.admin.dto.DeleteParamRequest;
import org.exframework.portal.admin.dto.ParamKey;
import org.exframework.portal.admin.dto.PostParamQueryRequest;
import org.exframework.portal.dao.PortalCoreAccountParameterDao;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.portal.metadata.sys.AccountParameter;
import org.exframework.portal.service.sys.PortalCoreAccountService;
import org.exframework.portal.service.sys.PortalCoreSystemParameterService;
import org.exframework.portal.web.dto.PostConditionQueryRequest;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.entity.ApiResponsePage;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class AdminParamService {

	@Autowired
	PortalCoreAccountParameterDao portalCoreAccountParameterDao;
	
	@Autowired
	PortalCoreSystemParameterService portalCoreSystemParameterService;
	
	@Autowired
	PortalCoreAccountService portalCoreAccountService;

	public ApiResponseData<String> get(String code, String account) {
		return new ApiResponseData<String>(portalCoreSystemParameterService.getAccoutParameter(account, code));
	}
	
	public ApiResponsePage<AccountParameter> query(PostParamQueryRequest request) {
		Collection<FilterCondition> params = FilterCondition.fromDTO(request);
		PageModel<AccountParameter> model = portalCoreAccountParameterDao.query(params, request.getPage());
		return new ApiResponsePage<AccountParameter>(model);
	}
	
	public ApiResponsePage<AccountParameter> query(PostConditionQueryRequest request) {
		PageModel<AccountParameter> model = portalCoreAccountParameterDao.query(Arrays.asList(request.getConditions()), request.getPage());
		return new ApiResponsePage<AccountParameter>(model);
	}

	public ApiResponseData<Boolean> post(AccountParameter param) {
		return new ApiResponseData<Boolean>(portalCoreSystemParameterService.putAccountKey(param.getAccount(), param.getCode(), param.getName(), param.getValue(), param.getDescription()));
	}
	
	public ApiResponseData<Long> delete(DeleteParamRequest request) {
		long count = 0L;
		for(ParamKey key : request.getKeys()) {
			boolean success = portalCoreSystemParameterService.removeAccountKey(key.getAccount(), key.getCode());
			if(success) {
				count ++;
			}
		}
		return new ApiResponseData<Long>(count);
	}
}
