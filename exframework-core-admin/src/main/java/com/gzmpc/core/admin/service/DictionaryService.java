package com.gzmpc.core.admin.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.core.admin.dto.PostDictionaryQueryRequest;
import com.gzmpc.dao.DictionaryDao;
import com.gzmpc.metadata.FilterCondition;
import com.gzmpc.metadata.dict.DictionaryItem;
import com.gzmpc.service.sys.AccountService;
import com.gzmpc.service.sys.DdlService;
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
public class DictionaryService extends  DdlService {

	@Autowired
	DictionaryDao dictionaryDao;
	
	@Autowired
	AccountService accountService;

//	public ApiResponseData<String> get(String code, String account) {
//		return new ApiResponseData<String>(ddlSerice.g);
//	}
	
	public ApiResponsePage<DictionaryItem> query(PostDictionaryQueryRequest request) {
		Collection<FilterCondition> params = FilterCondition.fromDTO(request);
		PageModel<DictionaryItem> model = dictionaryDao.query(params, request.getPage());
		return new ApiResponsePage<DictionaryItem>(model);
	}

	public ApiResponseData<Boolean> post(DictionaryItem dictionary) {
		int success = dictionaryDao.insert(dictionary);
		return new ApiResponseData<Boolean>(success > 0);
	}
	
//	public ApiResponseData<Long> delete(DeleteParamRequest request) {
//		long count = 0l;
//		for(ParamKey key : request.getKeys()) {
//			boolean success = systemParameterService.removeAccountKey(key.getAccount(), key.getCode());
//			if(success) {
//				count ++;
//			}
//		}
//		return new ApiResponseData<Long>(count);
//	}
}
