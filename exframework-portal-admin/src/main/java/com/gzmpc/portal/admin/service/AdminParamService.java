package com.gzmpc.portal.admin.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.admin.dto.DeleteParamRequest;
import com.gzmpc.portal.admin.dto.ParamKey;
import com.gzmpc.portal.admin.dto.PostParamQueryRequest;
import com.gzmpc.portal.dao.AccountParameterDao;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.sys.AccountParameter;
import com.gzmpc.portal.service.sys.AccountService;
import com.gzmpc.portal.service.sys.SystemParameterService;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.entity.ApiResponsePage;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class AdminParamService {

	@Autowired
	AccountParameterDao accountParameterDao;
	
	@Autowired
	SystemParameterService systemParameterService;
	
	@Autowired
	AccountService accountService;

	public ApiResponseData<String> get(String code, String account) {
		return new ApiResponseData<String>(systemParameterService.getAccoutParameter(account, code));
	}
	
	public ApiResponsePage<AccountParameter> query(PostParamQueryRequest request) {
		Collection<FilterCondition> params = FilterCondition.fromDTO(request);
		PageModel<AccountParameter> model = accountParameterDao.query(params, request.getPage());
		return new ApiResponsePage<AccountParameter>(model);
	}

	public ApiResponseData<Boolean> post(AccountParameter param) {
		return new ApiResponseData<Boolean>(systemParameterService.putAccountKey(param.getAccount(), param.getCode(), param.getName(), param.getValue(), param.getDescription()));
	}
	
	public ApiResponseData<Long> delete(DeleteParamRequest request) {
		long count = 0l;
		for(ParamKey key : request.getKeys()) {
			boolean success = systemParameterService.removeAccountKey(key.getAccount(), key.getCode());
			if(success) {
				count ++;
			}
		}
		return new ApiResponseData<Long>(count);
	}
}
