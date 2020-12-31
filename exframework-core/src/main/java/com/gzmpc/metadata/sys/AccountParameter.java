package com.gzmpc.metadata.sys;

import com.gzmpc.metadata.Meta;

/**
 * 用户参数
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public class AccountParameter extends Meta {

	private static final long serialVersionUID = -8543010770093074083L;

	/**
	 * 帐号
	 */
	private String account;
	
	/**
	 * 值
	 */
	private String value;
	
	public AccountParameter() {
		
	}
	
	public AccountParameter( Account account, String key, String name, String value, String comment) {
		this.account = account.getAccountId();
		this.setCode(key);
		this.setName(name);
		this.value = value;
		this.setComment(comment);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
