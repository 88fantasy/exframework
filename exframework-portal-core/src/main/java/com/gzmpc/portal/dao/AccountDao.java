package com.gzmpc.portal.dao;

import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.sys.Account;

/**
 *
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface AccountDao {

	Account getAccount(String accountId) throws NotFoundException;
	
	boolean changePassword(Account account, String oldPass, String newPass);
	
}
