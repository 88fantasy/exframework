package com.gzmpc.portal.web.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.web.entity.LoginDTO;
import com.gzmpc.portal.web.vo.LoginResponse;
import com.gzmpc.support.rest.entity.ApiResponseData;

public interface LoginService {

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
