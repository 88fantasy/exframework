package org.exframework.portal.dao;

import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.sys.Account;

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
