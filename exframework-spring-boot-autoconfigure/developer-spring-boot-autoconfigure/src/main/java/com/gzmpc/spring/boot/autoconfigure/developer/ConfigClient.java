package com.gzmpc.spring.boot.autoconfigure.developer;

import java.util.List;
import java.util.Map;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Post;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.exception.ApiException;

/**
 *
 * Author: rwe
 * Date: 2021年5月25日
 *
 * Copyright @ 2021 
 * 
 */
@BaseRequest(
	    baseURL = "https://develop.gzmpc.com/api/config",     // 默认域名
	    contentType = "application/json"
	)
public interface ConfigClient {

	@Post(url = "/v1/param/findKeys/${appCode}", dataType = "json")
	public ApiResponseData<Map<String,ParamDTO>> sendText(@DataVariable("appCode") String appCode, @Body List<String> request) throws ApiException;

	
}
