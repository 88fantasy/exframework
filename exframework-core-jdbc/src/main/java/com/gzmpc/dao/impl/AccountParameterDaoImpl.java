package com.gzmpc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzmpc.core.entity.AccountParameterDO;
import com.gzmpc.core.mapper.AccountParameterMapper;
import com.gzmpc.core.util.MapperUtil;
import com.gzmpc.dao.AccountParameterDao;
import com.gzmpc.metadata.FilterCondition;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.AccountParameter;
import com.gzmpc.support.common.entity.PageModel;

/**
 * 权限数据类
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class AccountParameterDaoImpl implements AccountParameterDao, MapperUtil<AccountParameterDO> {
	
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
		return accountParameterMapper.selectOne(getUniqueWrapper(account, key));
	}

	@Override
	public boolean putAccountKey(Account account, String key, String value) {
		return putAccountKey(account, key, null, value, null);
	}
	

	@Override
	public boolean putAccountKey(Account account, String code, String name, String value, String description) {
		Wrapper<AccountParameterDO> wrapper = getUniqueWrapper(account, code);
		AccountParameterDO entity = accountParameterMapper.selectOne(wrapper);
		if(entity  == null) {
			entity = new AccountParameterDO();
			entity.setCode(code);
			entity.setAccount(account.getAccountId());
			entity.setName(name);
			entity.setValue(value);
			entity.setDescription(description);
			return accountParameterMapper.insert(entity) > 0;
		}
		else {
			entity.setName(name);
			entity.setValue(value);
			entity.setDescription(description);
			return accountParameterMapper.update(entity, wrapper) > 0;
		}
	}

	@Override
	public boolean removeAccountKey(Account account, String key) {
		return accountParameterMapper.delete(getUniqueWrapper(account, key)) > 0;
	}


	private LambdaQueryWrapper<AccountParameterDO> getUniqueWrapper(Account account, String key) {
		return new QueryWrapper<AccountParameterDO>().lambda().eq(AccountParameterDO::getAccount, account.getAccountId()).eq(AccountParameterDO::getCode, key);
	}

	@Override
	public PageModel<AccountParameter> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page) {
		Page<AccountParameterDO> p = accountParameterMapper.selectPage(new Page<AccountParameterDO>(page.getCurrent(), page.getPageSize()), wrapperFromCondition(params));
		return modelFromPage(p,AccountParameter.class);
	}

	@Override
	public List<AccountParameter> list(Collection<FilterCondition> params) {
		return new ArrayList<AccountParameter>(accountParameterMapper.selectList(wrapperFromCondition(params)));
	}

}
