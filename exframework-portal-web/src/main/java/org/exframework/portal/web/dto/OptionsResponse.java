package org.exframework.portal.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author rwe
 * @since 2021年4月5日
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

	public OptionsResponse setLabel(String label) {
		this.label = label;
		return this;
	}

	public String getValue() {
		return value;
	}

	public OptionsResponse setValue(String value) {
		this.value = value;
		return this;
	}
}
