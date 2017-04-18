package com.gzmpc.wf.service;

import java.util.Iterator;

import com.gzmpc.metadata.sys.Account;

public interface WfAccountService {

	public Account findAccount(String id);
	
	public Iterator<Account> listAccountByRoleid(String roleid);
}
