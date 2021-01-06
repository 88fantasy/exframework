package com.gzmpc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gzmpc.core.entity.AccountDO;
import com.gzmpc.core.mapper.AccountMapper;
import com.gzmpc.dao.AccountDao;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.sys.Account;

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
		return accountMapper.selectOne(new QueryWrapper<AccountDO>().eq("accountId", accountId));
	}
}
