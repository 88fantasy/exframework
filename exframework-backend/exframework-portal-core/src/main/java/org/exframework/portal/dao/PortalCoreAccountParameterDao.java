package org.exframework.portal.dao;

import java.util.Collection;
import java.util.List;

import org.exframework.support.common.entity.FilterCondition;
import org.springframework.lang.Nullable;

import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.metadata.sys.AccountParameter;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

/**
 * 权限 Dao
 * @author rwe
 * @since Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PortalCoreAccountParameterDao {

	@Nullable
	String getAccoutParameter(Account account, String code);
	
	boolean putAccountKey(Account account, String key, String value);
	
	boolean putAccountKey(Account account, String key, String name, String value, String description);
	
	boolean removeAccountKey(Account account, String code);
	
	PageModel<AccountParameter> query(Collection<FilterCondition> params, Page page);
	
	List<AccountParameter> list(Collection<FilterCondition> params);
	
}
