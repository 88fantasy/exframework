package com.gzmpc.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.AccountParameter;

/**
 *
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_accountparam")
public class AccountParameterDO extends AccountParameter {

	private static final long serialVersionUID = 1988325593021911178L;
	
	/**
	 * 代码
	 */
	@TableId
	private String code;
	
	/**
	 * 名称
	 */
	@TableField
	private String name;
	
	/**
	 * 备注
	 */
	@TableField
	private String comment;
	
	/**
	 * 帐号
	 */
	@TableField
	private String account;
	
	/**
	 * 值
	 */
	@TableField
	private String value;
	

	public AccountParameterDO(Account account, String key, String name, String value, String comment) {
		super(account, key, name, value, comment);
	}

}
