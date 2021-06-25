package org.exframework.portal.web.entity;

import javax.validation.constraints.NotEmpty;

/**
 *
 * Author: rwe
 * Date: Dec 27, 2020
 *
 * Copyright @ 2020 
 * 
 */
public class LoginDTO {

	/**
	 * 帐号
	 */
	@NotEmpty
	private String username;
	
	/**
	 * 密码
	 */
	@NotEmpty
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
