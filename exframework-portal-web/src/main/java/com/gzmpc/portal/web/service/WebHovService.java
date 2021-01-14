package com.gzmpc.portal.web.service;

import java.text.MessageFormat;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.hov.HovBase;
import com.gzmpc.portal.metadata.hov.IHovDao;
import com.gzmpc.portal.pub.PageRequest;
import com.gzmpc.portal.service.sys.HovService;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.common.util.SpringContextUtils;
import com.gzmpc.support.rest.entity.ApiResponsePage;
import com.gzmpc.support.rest.enums.ResultCode;

/**
 *
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Service
public class WebHovService {
	
	private Logger log = LoggerFactory.getLogger(WebHovService.class.getName());

	@Autowired
	HovService hovService;
	

	public ApiResponsePage<?> query(String code, Object request) {
		HovBase hov = hovService.findByKey(code);
		if(hov != null) {
			String requestClassName = hov.getRequestClass();
			String dataClassName = hov.getDataClass();
			Class<? extends PageRequest> requestClass = null;
			Class<? extends IHovDao<?>> dataClass = null;
			try {
				requestClass = hovService.getRequestClass(hov);
			} catch ( Exception e) {
				String message = MessageFormat.format("请求实体类{0}转换失败", requestClassName);
				log.error(message, e);
				return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR,message, null);
			}
			try {
				dataClass = hovService.getDataClass(hov);
			} catch ( Exception e) {
				String message = MessageFormat.format("返回实体类{0}转换失败", dataClassName);
				log.error(message, e);
				return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR,message, null);
			}
			
			return query(request, requestClass, dataClass);
		}
		else {
			return ApiResponsePage.notFound("找不到此hov") ;
		}
	}
	
	private <U extends PageRequest,V extends IHovDao<?>> ApiResponsePage<V> query(Object request, Class<U> requestClass, Class<V> responseClass) {
		U u = BeanUtils.instantiateClass(requestClass);
		BeanUtils.copyProperties(request, u);
		Collection<FilterCondition> params = FilterCondition.fromDTO(u);
		Page page = u.getPage();
		try {
			V v = SpringContextUtils.getBeanByClass(responseClass);
			PageModel<V> model = (PageModel<V>) v.query(params, page);
			return new ApiResponsePage<V>(model);
		}
		catch ( BeansException e ) {
			return new ApiResponsePage<V>(ResultCode.INTERNAL_SERVER_ERROR, responseClass+"缺少实现: "+e.getMessage(), null);
		}
	}
}
