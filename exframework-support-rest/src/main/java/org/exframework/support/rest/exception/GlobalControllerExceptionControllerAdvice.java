package org.exframework.support.rest.exception;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;

/**
 *
 * @author rwe
 * @since Nov 29, 2020
 *
 * Copyright @ 2020 
 * 接口全局处理
 */
@RestControllerAdvice
public class GlobalControllerExceptionControllerAdvice {

	private static Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionControllerAdvice.class.getName());
	
	public static final String PARAMS_ERROR = "参数校验错误";

	/**
	 * 参数范围校验错误
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseData<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		logger.error(e.getMessage(), e);
        return new ApiResponseData<>(ResultCode.BAD_REQUEST, PARAMS_ERROR, e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
    }
	
	@ExceptionHandler(ApiException.class)
	public ApiResponseData<Object> apiExceptionHandler(ApiException e) {
		errorHandle(e);
		return new ApiResponseData<>(e.getCode(), e.getMessage(), false, e.getData());
	}
	
	@ExceptionHandler(ServerException.class)
	public ApiResponseData<Object> serverExceptionHandler(ServerException e) {
		errorHandle(e);
		return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiResponseData<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
		errorHandle(e);
		return new ApiResponseData<>(ResultCode.BAD_REQUEST, "请求参数错误,请检查", e.getMessage());
	}

	@ExceptionHandler(NullPointerException.class)
	public ApiResponseData<Object> nullPointerExceptionHandler(NullPointerException e) {
		errorHandle(e);
		return new ApiResponseData<>(ResultCode.SERVICE_UNAVAILABLE, "空指针错误,请联系管理员", e.getMessage());
	}

	@ExceptionHandler(IOException.class)
	public ApiResponseData<Object> nullPointerExceptionHandler(IOException e) {
		errorHandle(e);
		return new ApiResponseData<>(ResultCode.SERVICE_UNAVAILABLE, "文件错误,请联系管理员",  e.getMessage());
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ApiResponseData<Object> exceptionHandler(InvalidFormatException e) {
		errorHandle(e);
		return new ApiResponseData<>(ResultCode.NOT_ACCEPTABLE, "格式错误:"+e.getMessage());
	}


	@ExceptionHandler(Exception.class)
	public ApiResponseData<Object> exceptionHandler(Exception e) {
		errorHandle(e);
		return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	private void errorHandle(Exception e) {
		logger.error(MessageFormat.format("全局错误处理:{0}", e.getMessage()), e);
	}
}
