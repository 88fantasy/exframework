package org.exframework.portal.web.service;

import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.service.sys.PortalCoreAccountService;
import org.exframework.portal.web.entity.LoginDTO;
import org.exframework.portal.web.vo.LoginResponse;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录业务类
 * 
 * @author rwe
 *
 */

public class DefaultPortalWebBaseLoginServiceImpl implements PortalWebBaseLoginService {

	private final String sessionKey = "session_account";

	@Autowired
	PortalCoreAccountService portalCoreAccountService;

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
		Account account = portalCoreAccountService.getAccount(dto.getUsername());
		if (account == null) {
			return new ApiResponseData<>(ResultCode.BAD_REQUEST, "帐号或密码错误", null);
		} else if (portalCoreAccountService.isValid(account, dto.getPassword())) {
			return new ApiResponseData<>(null);
		} else {
			return new ApiResponseData<>(ResultCode.BAD_REQUEST, "帐号或密码错误", null);
		}
	}

}