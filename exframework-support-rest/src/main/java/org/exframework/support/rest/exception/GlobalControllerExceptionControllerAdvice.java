package org.exframework.support.rest.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;

/**
 *
 * Author: rwe
 * Date: Nov 29, 2020
 *
 * Copyright @ 2020 
 * 接口全局处理
 */
@RestControllerAdvice
public class GlobalControllerExceptionControllerAdvice {
	
	public static final String PARAMS_ERROR = "参数校验错误";

	/**
	 * 参数范围校验错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseData<List<String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		// 从异常对象中拿到ObjectError对象
        return new ApiResponseData<List<String>>(ResultCode.BAD_REQUEST, PARAMS_ERROR, e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
    }
	
	@ExceptionHandler(ApiException.class)
	public ApiResponseData<String> ApiExceptionHandler(ApiException e) {
	    return new ApiResponseData<String>(e.getCode(), e.getMessage(), false, "客户端请求错误");
	}
	
	@ExceptionHandler(ServerException.class)
	public ApiResponseData<String> ServerExceptionHandler(ServerException e) {
	    return new ApiResponseData<String>(e.getCode(), e.getMessage(), false, "响应失败");
	}
}
