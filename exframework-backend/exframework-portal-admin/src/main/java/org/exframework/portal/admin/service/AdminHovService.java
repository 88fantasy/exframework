package org.exframework.portal.admin.service;

import org.exframework.portal.admin.dto.PostHovQueryRequest;
import org.exframework.portal.dao.PortalCoreHovDao;
import org.exframework.portal.metadata.hov.Hov;
import org.exframework.portal.service.sys.PortalCoreAccountService;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.rest.entity.ApiResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 参数业务类 Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
@Service
public class AdminHovService {

	@Autowired
	PortalCoreHovDao portalCoreHovDao;
	
	@Autowired
	PortalCoreAccountService portalCoreAccountService;
	
	@Autowired
	ApplicationContext applicationContext;

//	public ApiResponseData<String> get(String code, String account) {
//		return new ApiResponseData<String>(ddlSerice.g);
//	}
	
	public ApiResponsePage<Hov> query(PostHovQueryRequest request) {
		Collection<FilterCondition> params = FilterCondition.fromDTO(request);
		PageModel<Hov> model = portalCoreHovDao.query(params, request.getPage());
		return new ApiResponsePage<Hov>(model);
	}
	

//	public ApiResponseData<Boolean> post(PostDataItemPostRequest request) {
//		DataItem dataitem = request.getItem();
//		if(request.isCreate()) {
//			DataItem item = portalCoreDataItemDao.findByKey(dataitem.getCode());
//			if(item != null) {
//				return new ApiResponseData<Boolean>(ResultCode.BAD_REQUEST, "字典已存在", false);
//			}
//			else {
//				return new ApiResponseData<Boolean>(portalCoreDataItemDao.insert(dataitem) > 0 );
//			}
//		}
//		else {
//			return new ApiResponseData<Boolean>(portalCoreDataItemDao.update(dataitem) > 0);
//		}
//	}
	
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
