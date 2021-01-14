package com.gzmpc.portal.web.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.portal.metadata.hov.HovQueryParams;
import com.gzmpc.portal.metadata.hov.IHovDao;
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
	

	public ApiResponsePage<?> query(String code, String requestJson) {
		Hov hov = hovService.findByKey(code);
		if(hov != null) {
			HovQueryParams[] params = hov.getQueryParams();
			String dataClassName = hov.getDataClass();
			Class<? extends IHovDao<?>> dataClass = null;
			try {
				dataClass = (Class<? extends IHovDao<?>>) Class.forName(hov.getDataClass());
			} catch ( Exception e) {
				String message = MessageFormat.format("返回实体类{0}转换失败", dataClassName);
				log.error(message, e);
				return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR,message, null);
			}
			JSONObject request = JSON.parseObject(requestJson);
			Page page = Page.DEFAULT;
			if(request.containsKey("page")) {
				page = request.getObject("page", Page.class);
				request.remove("page");
			}
			
			List<FilterCondition> fcs = new ArrayList<FilterCondition>();
			for(HovQueryParams param : params) {
				String dataIndex = param.getDataIndex();
				if(request.containsKey(dataIndex)) {
					Object value = request.get(dataIndex);
					if( value != null) {
						FilterCondition fc = new FilterCondition(dataIndex, value);
						fcs.add(fc);
					}
				}
			}
			
			try {
				IHovDao<?> v = SpringContextUtils.getBeanByClass(dataClass);
				PageModel<?> model = v.query(fcs, page);
				return new ApiResponsePage<>(model);
			}
			catch ( BeansException e ) {
				return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR, dataClassName+"缺少实现: "+e.getMessage(), null);
			}
			
		}
		else {
			return ApiResponsePage.notFound("找不到此hov") ;
		}
	}
}
