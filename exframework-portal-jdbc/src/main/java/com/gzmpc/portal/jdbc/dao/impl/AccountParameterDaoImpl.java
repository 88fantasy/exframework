package com.gzmpc.portal.jdbc.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.portal.dao.AccountParameterDao;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.metadata.sys.AccountParameter;
import com.gzmpc.portal.jdbc.entity.AccountParameterDO;
import com.gzmpc.portal.jdbc.mapper.AccountParameterMapper;
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
			entity.setAccount(account.getAccount());
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
		return new QueryWrapper<AccountParameterDO>().lambda().eq(AccountParameterDO::getAccount, account.getAccount()).eq(AccountParameterDO::getCode, key);
	}

	@Override
	public PageModel<AccountParameter> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page) {
		return accountParameterMapper.query(params, page, AccountParameter.class);
	}

	@Override
	public List<AccountParameter> list(Collection<FilterCondition> params) {
		return accountParameterMapper.list(params, AccountParameter.class);
	}

}
