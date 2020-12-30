package com.gzmpc.core.entity;

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

	public AccountParameterDO(Account account, String key, String name, String value, String comment) {
		super(account, key, name, value, comment);
	}

}
