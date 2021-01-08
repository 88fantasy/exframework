package com.gzmpc.core.web.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.support.rest.enums.ResultCode;

/**
 *
 * Author: rwe
 * Date: Nov 29, 2020
 *
 * Copyright @ 2020 
 * 接口错误处理
 */
@RestControllerAdvice( basePackages = "com.gzmpc.core.web.controller")
public class WebControllerExceptionControllerAdvice {
	
	@ExceptionHandler(NotFoundException.class)
	public ApiResponseData<String> ApiExceptionHandler(NotFoundException e) {
	    return new ApiResponseData<String>(ResultCode.NOT_FOUND, e.getMessage(), null);
	}
	
	@ExceptionHandler(NotAuthorizedException.class)
	public ApiResponseData<String> ServerExceptionHandler(NotAuthorizedException e) {
	    return new ApiResponseData<String>(ResultCode.UNAUTHORIZED, e.getMessage(), null);
	}
}
