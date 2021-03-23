package com.gzmpc.portal.admin.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.admin.dto.PostHovQueryRequest;
import com.gzmpc.portal.dao.HovDao;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.portal.metadata.hov.IHovDao;
import com.gzmpc.portal.service.sys.AccountService;
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
public class AdminHovService {

	@Autowired
	HovDao hovDao;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ApplicationContext applicationContext;

//	public ApiResponseData<String> get(String code, String account) {
//		return new ApiResponseData<String>(ddlSerice.g);
//	}
	
	public ApiResponsePage<Hov> query(PostHovQueryRequest request) {
		Collection<FilterCondition> params = FilterCondition.fromDTO(request);
		PageModel<Hov> model = hovDao.query(params, request.getPage());
		return new ApiResponsePage<Hov>(model);
	}
	

//	public ApiResponseData<Boolean> post(PostDataItemPostRequest request) {
//		DataItem dataitem = request.getItem();
//		if(request.isCreate()) {
//			DataItem item = dataItemDao.findByKey(dataitem.getCode());
//			if(item != null) {
//				return new ApiResponseData<Boolean>(ResultCode.BAD_REQUEST, "字典已存在", false);
//			}
//			else {
//				return new ApiResponseData<Boolean>(dataItemDao.insert(dataitem) > 0 );
//			}
//		}
//		else {
//			return new ApiResponseData<Boolean>(dataItemDao.update(dataitem) > 0);
//		}
//	}
	
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
