package com.gzmpc.login;

import javax.servlet.http.HttpServletRequest;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.metadata.sys.Account;

public interface LoginService {
	
	public String getSessionKey();

	public Account getAccount(HttpServletRequest request) throws NotAuthorizedException;
	
	public boolean isSSO();
	
}
