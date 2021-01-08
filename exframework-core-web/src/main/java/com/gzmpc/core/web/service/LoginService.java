package com.gzmpc.core.web.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gzmpc.core.web.entity.LoginDTO;
import com.gzmpc.core.web.vo.LoginResponse;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.metadata.sys.Account;
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
