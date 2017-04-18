package com.gzmpc.login;

import org.springframework.stereotype.Service;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.metadata.sys.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * <p>
 * Title: 账号登陆后台
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author rwe
 * @version 1.0
 */

@Service("loginService")
public class DefaultLoginServiceImpl implements LoginService {
	
	private final String sessionKey = "session_account";

	@Override
	public String getSessionKey() {
		return sessionKey;
	}
	
	@Override
	public Account getAccount(HttpServletRequest request) throws NotAuthorizedException {
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute(sessionKey);
		if(account == null ) {
			throw new NotAuthorizedException("尚未登录");
		}
		else {
			return account;
		}
	}

	@Override
	public boolean isSSO() {
		return false;
	}
	
	
}