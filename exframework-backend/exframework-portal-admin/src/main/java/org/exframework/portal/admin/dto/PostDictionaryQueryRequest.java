package org.exframework.portal.admin.dto;

import io.swagger.annotations.ApiModel;
import org.exframework.portal.web.dto.GlobalPostQueryRequest;

/**
 * 查询字典请求
 * @author rwe
 * @since Jan 5, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value="查询字典请求")
public class PostDictionaryQueryRequest extends GlobalPostQueryRequest {

	public PostDictionaryQueryRequest() {
		super();
	}
}
