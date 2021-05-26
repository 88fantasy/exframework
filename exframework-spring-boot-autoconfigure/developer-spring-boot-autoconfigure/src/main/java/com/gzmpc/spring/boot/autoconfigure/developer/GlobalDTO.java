package com.gzmpc.spring.boot.autoconfigure.developer;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @author rwe
* @version 创建时间：Oct 16, 2020 11:08:52 AM
* 全局 DTO
*/

@ApiModel(value = "保存参数DTO")
public class GlobalDTO {

	@ApiModelProperty(value = "系统code", required = true)
	@NotNull
	private String appCode;

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
}
