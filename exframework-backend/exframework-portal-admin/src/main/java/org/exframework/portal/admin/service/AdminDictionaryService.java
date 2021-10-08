package org.exframework.portal.admin.service;

import org.exframework.portal.admin.dto.PostDictionaryPostRequest;
import org.exframework.portal.admin.dto.PostDictionaryQueryRequest;
import org.exframework.portal.dao.PortalCoreDictionaryDao;
import org.exframework.portal.metadata.dict.DictionaryItem;
import org.exframework.portal.service.sys.PortalCoreAccountService;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.entity.ApiResponsePage;
import org.exframework.support.rest.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class AdminDictionaryService {

	@Autowired
	PortalCoreDictionaryDao portalCoreDictionaryDao;

	@Autowired
	PortalCoreAccountService portalCoreAccountService;

//	public ApiResponseData<String> get(String code, String account) {
//		return new ApiResponseData<String>(ddlSerice.g);
//	}

	public ApiResponsePage<DictionaryItem> query(PostDictionaryQueryRequest request) {
		Collection<FilterCondition> params = FilterCondition.fromDTO(request);
		PageModel<DictionaryItem> model = portalCoreDictionaryDao.query(params, request.getPage());
		return new ApiResponsePage<DictionaryItem>(model);
	}

	public ApiResponseData<Boolean> post(PostDictionaryPostRequest request) {
		DictionaryItem dictionary = request.getItem();
		if (request.isCreate()) {
			DictionaryItem dict = portalCoreDictionaryDao.findByKey(dictionary.getCode());
			if (dict != null) {
				return new ApiResponseData<Boolean>(ResultCode.BAD_REQUEST, "字典已存在", false);
			}
		}
		return new ApiResponseData<Boolean>(
				portalCoreDictionaryDao.saveDictionary(dictionary.getCode(), dictionary.getName(), dictionary.getValue()));
	}

//	public ApiResponseData<Long> delete(DeleteParamRequest request) {
//		long count = 0l;
//		for(ParamKey key : request.getKeys()) {
//			boolean success = portalCoreSystemParameterService.removeAccountKey(key.getAccount(), key.getCode());
//			if(success) {
//				count ++;
//			}
//		}
//		return new ApiResponseData<Long>(count);
//	}
}
