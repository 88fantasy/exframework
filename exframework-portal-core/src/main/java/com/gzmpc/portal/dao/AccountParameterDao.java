package com.gzmpc.portal.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.lang.Nullable;

import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.metadata.sys.AccountParameter;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;

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
	String getAccoutParameter(Account account, String code);
	
	boolean putAccountKey(Account account, String key, String value);
	
	boolean putAccountKey(Account account, String key, String name, String value, String description);
	
	boolean removeAccountKey(Account account, String code);
	
	PageModel<AccountParameter> query(Collection<FilterCondition> params, Page page);
	
	List<AccountParameter> list(Collection<FilterCondition> params);
	
}
