package com.gzmpc.service;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.sys.Account;

public interface AccountService {

	public Account getAccount(String accountid);
	
	public boolean isValid(Account acount,String password);
	
	/**
	 * 
	 * @param accountid
	 * @param force	true 强制更新, 否则先取缓冲
	 * @return
	 */
	public String getAccountName(String accountid,boolean force)  throws NotFoundException;
}
