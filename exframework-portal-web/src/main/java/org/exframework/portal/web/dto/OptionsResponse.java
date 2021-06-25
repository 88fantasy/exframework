package org.exframework.portal.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * Author: rwe
 * Date: 2021年4月5日
 *
 * Copyright @ 2021 
 * 
 */
@ApiModel("下拉选项")
public class OptionsResponse {

	@ApiModelProperty("显示")
	private String label;
	
	@ApiModelProperty("值")
	private String value;

	
	public OptionsResponse() {
		super();
	}

	public OptionsResponse(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
