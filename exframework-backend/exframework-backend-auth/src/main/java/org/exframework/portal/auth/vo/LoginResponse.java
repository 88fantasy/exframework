package org.exframework.portal.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author rwe
* @version 创建时间：2021年4月19日 下午1:35:05
* 类说明
*/

@ApiModel(value = "登录响应")
public class LoginResponse {

	@NotNull
	@ApiModelProperty(value = "登录结果", required = true)
	private String status;
	
	@ApiModelProperty(value = "token")
	private String token;
	
	@ApiModelProperty(value = "权限")
	private List<String>  permissions;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public LoginResponse setPermissions(List<String> permissions) {
		this.permissions = permissions;
		return this;
	}
}
