package com.gzmpc.web.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.AccountParameterDao;
import com.gzmpc.metadata.FilterCondition;
import com.gzmpc.metadata.sys.AccountParameter;
import com.gzmpc.service.sys.AccountService;
import com.gzmpc.service.sys.SystemParameterService;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.entity.ApiResponsePage;
import com.gzmpc.web.entity.dto.DeleteParamRequest;
import com.gzmpc.web.entity.dto.DeleteParamRequest.ParamKey;
import com.gzmpc.web.entity.dto.PostParamQueryRequest;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class ParamService {

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