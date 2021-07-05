package org.exframework.portal.web.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.web.entity.LoginDTO;
import org.exframework.portal.web.vo.LoginResponse;
import org.exframework.support.rest.entity.ApiResponseData;

public interface PortalWebBaseLoginService {

	default Account getAccount() throws NotAuthorizedException {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	    HttpServletRequest request = servletRequestAttributes.getRequest();
	    return getAccount(request);
	}
	
	Account getAccount(HttpServletRequest request) throws NotAuthorizedException;
	
	default boolean isSSO() {
		return false;
	}
	

	
	ApiResponseData<LoginResponse> login(LoginDTO dto) throws NotAuthorizedException;
	
}
