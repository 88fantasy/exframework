package com.gzmpc.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.AccountParameterDO;
import com.gzmpc.core.entity.mapper.AccountParameterMapper;
import com.gzmpc.dao.AccountParameterDao;
import com.gzmpc.metadata.sys.Account;

/**
 * 权限数据类
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class AccountParameterDaoImpl implements AccountParameterDao {
	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccountParameterMapper accountParameterMapper;

	@Override
	public String getAccoutParameter(Account account, String key) {
		AccountParameterDO entity = getAccoutParameterEntity(account, key);
		if(entity != null) {
			return entity.getValue();
		}
		else {
			return null;
		}
	}
	
	public AccountParameterDO getAccoutParameterEntity(Account account, String key) {
		return accountParameterMapper.getOne(getUniqueWrapper(account, key));
	}

	@Override
	public boolean putAccountKey(Account account, String key, String value) {
		return putAccountKey(account, key, null, value, null);
	}
	

	@Override
	public boolean putAccountKey(Account account, String key, String name, String value, String comment) {
		AccountParameterDO entity = new AccountParameterDO(account, key, name, value, comment);
		return accountParameterMapper.saveOrUpdate(entity, getUniqueWrapper(account, key));
	}

	@Override
	public boolean removeAccountKey(Account account, String key) {
		return accountParameterMapper.remove(getUniqueWrapper(account, key));
	}


	private QueryWrapper<AccountParameterDO> getUniqueWrapper(Account account, String key) {
		return new QueryWrapper<AccountParameterDO>().eq("account", account.getAccountId()).eq("key", key);
	}

}
