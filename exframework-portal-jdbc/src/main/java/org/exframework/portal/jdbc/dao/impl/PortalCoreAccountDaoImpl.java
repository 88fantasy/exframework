package org.exframework.portal.jdbc.dao.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.exframework.portal.dao.PortalCoreAccountDao;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.jdbc.entity.AccountDO;
import org.exframework.portal.jdbc.mapper.AccountMapper;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class PortalCoreAccountDaoImpl implements PortalCoreAccountDao {

	@Autowired
	AccountMapper accountMapper;
	
	@Override
	public Account getAccount(String accountId) throws NotFoundException {
		return getAccountDO(accountId);
	}

	@Override
	public boolean changePassword(Account account, String oldPass, String newPass) {
		accountMapper.update(null,Wrappers.<AccountDO>lambdaUpdate().set(AccountDO::getPassword, newPass).eq(AccountDO::getAccount, account.getAccount()));
		return false;
	}

	private AccountDO getAccountDO(String accountId) throws NotFoundException {
		AccountDO entity = accountMapper.selectOne(new QueryWrapper<AccountDO>().lambda().eq(AccountDO::getAccount, accountId));
		if( entity == null ) {
			throw new NotFoundException(MessageFormat.format("不存在该帐号{0}", accountId));
		}
		return entity;
	}
}
