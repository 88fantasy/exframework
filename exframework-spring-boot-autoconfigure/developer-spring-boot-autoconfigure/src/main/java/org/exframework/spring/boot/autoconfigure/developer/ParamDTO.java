package org.exframework.spring.boot.autoconfigure.developer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
* @author rwe
* @version 创建时间：Oct 16, 2020 10:46:37 AM
* 参数DTO
*/

@ApiModel(value = "参数DTO")
public class ParamDTO extends GlobalDTO {

	@ApiModelProperty(value = "键", required = true)
	@NotNull
	private String paramKey;

	@ApiModelProperty(value = "名称", required = true)
	@NotNull
	private String paramName;
	
	@ApiModelProperty(value = "值", required = true)
	@NotNull
	private String value;

	@ApiModelProperty(value = "继承")
	private Boolean inherited;

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getInherited() {
		return inherited;
	}

	public void setInherited(Boolean inherited) {
		this.inherited = inherited;
	}

	
}
