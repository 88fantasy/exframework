package com.gzmpc.portal.admin.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.admin.dto.PostDictionaryPostRequest;
import com.gzmpc.portal.admin.dto.PostDictionaryQueryRequest;
import com.gzmpc.portal.dao.DictionaryDao;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.dict.DictionaryItem;
import com.gzmpc.portal.service.sys.AccountService;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.entity.ApiResponsePage;
import com.gzmpc.support.rest.enums.ResultCode;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class AdminDictionaryService {

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

	public ApiResponseData<Boolean> post(PostDictionaryPostRequest request) {
		DictionaryItem dictionary = request.getItem();
		if (request.isCreate()) {
			DictionaryItem dict = dictionaryDao.findByKey(dictionary.getCode());
			if (dict != null) {
				return new ApiResponseData<Boolean>(ResultCode.BAD_REQUEST, "字典已存在", false);
			}
		}
		return new ApiResponseData<Boolean>(
				dictionaryDao.saveDictionary(dictionary.getCode(), dictionary.getName(), dictionary.getValue()));
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
