package com.gzmpc.dao;

import org.springframework.lang.Nullable;

import com.gzmpc.metadata.sys.Account;

/**
 * 权限 Dao
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface AccountParameterDao {

	@Nullable
	String getAccoutParameter(Account account, String key);
	
	boolean putAccountKey(Account account, String key, String value);
	
	boolean putAccountKey(Account account, String key, String name, String value, String comment);
	
	boolean removeAccountKey(Account account, String key);
}
