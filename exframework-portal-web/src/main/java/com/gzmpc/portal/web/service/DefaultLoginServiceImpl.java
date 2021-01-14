package com.gzmpc.portal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.service.sys.AccountService;
import com.gzmpc.portal.web.entity.LoginDTO;
import com.gzmpc.portal.web.vo.LoginResponse;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.enums.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录业务类
 * 
 * @author rwe
 *
 */
@Service("loginService")
public class DefaultLoginServiceImpl implements LoginService {

	private final String sessionKey = "session_account";

	@Autowired
	AccountService accountService;

	@Override
	public Account getAccount(HttpServletRequest request) throws NotAuthorizedException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(sessionKey);
		if (account == null) {
			throw new NotAuthorizedException("尚未登录");
		} else {
			return account;
		}
	}

	@Override
	public ApiResponseData<LoginResponse> login(LoginDTO dto) {
		// 认证要求不区分帐号与密码错误
		Account account = accountService.getAccount(dto.getUsername());
		if (account == null) {
			return new ApiResponseData<LoginResponse>(ResultCode.BAD_REQUEST, "帐号或密码错误", null);
		} else if (accountService.isValid(account, dto.getPassword())) {
			return new ApiResponseData<LoginResponse>(null);
		} else {
			return new ApiResponseData<LoginResponse>(ResultCode.BAD_REQUEST, "帐号或密码错误", null);
		}
	}

}