package com.gzmpc.web.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.web.entity.LoginDTO;
import com.gzmpc.web.vo.LoginResponse;

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
