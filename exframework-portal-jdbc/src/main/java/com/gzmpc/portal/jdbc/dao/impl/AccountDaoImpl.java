package com.gzmpc.portal.jdbc.dao.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gzmpc.portal.dao.AccountDao;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.jdbc.entity.AccountDO;
import com.gzmpc.portal.jdbc.mapper.AccountMapper;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	AccountMapper accountMapper;
	
	@Override
	public Account getAccount(String accountId) throws NotFoundException {
		return getAccountDO(accountId);
	}

	@Override
	public boolean changePassword(Account account, String oldPass, String newPass) {
		accountMapper.update(null,Wrappers.<AccountDO>lambdaUpdate().set(AccountDO::getPassword, newPass).eq(AccountDO::getAccountId, account.getAccountId()));
		return false;
	}

	private AccountDO getAccountDO(String accountId) throws NotFoundException {
		AccountDO entity = accountMapper.selectOne(new QueryWrapper<AccountDO>().lambda().eq(AccountDO::getAccountId, accountId));
		if( entity == null ) {
			throw new NotFoundException(MessageFormat.format("不存在该帐号{0}", accountId));
		}
		return entity;
	}
}
