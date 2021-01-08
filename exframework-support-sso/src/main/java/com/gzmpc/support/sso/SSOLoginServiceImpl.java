package com.gzmpc.support.sso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;

import com.gzmpc.core.web.entity.LoginDTO;
import com.gzmpc.core.web.service.LoginService;
import com.gzmpc.core.web.vo.LoginResponse;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.support.rest.entity.ApiResponseData;

public class SSOLoginServiceImpl implements LoginService {
	private final String sessionKey = "SPRING_SECURITY_CONTEXT";

//	@Override
//	public String getSessionKey() {
//		return sessionKey;
//	}

	@Override
	public Account getAccount(HttpServletRequest request) throws NotAuthorizedException {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute(sessionKey);
		Authentication auth = securityContextImpl.getAuthentication();
		Account account = (Account) auth.getPrincipal();
		return account;
	}

	@Override
	public boolean isSSO() {
		return true;
	}

	@Override
	public ApiResponseData<LoginResponse> login(LoginDTO dto) throws NotAuthorizedException {
		// TODO Auto-generated method stub
		return null;
	}

}
