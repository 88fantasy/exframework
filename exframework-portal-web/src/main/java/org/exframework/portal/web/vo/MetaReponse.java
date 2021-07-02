package org.exframework.portal.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 元数据返回
 * @author rwe
 * @since Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel(value = "元数据返回")
public class MetaReponse {

	@ApiModelProperty(value="编码")
	private String code;
	
	@ApiModelProperty(value="名称")
	private String name;
	
	@ApiModelProperty(value="描述")
	private String  description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
