package com.gzmpc.support.rest.entity;


import com.gzmpc.support.rest.enums.ResultCode;
import com.gzmpc.support.rest.exception.GlobalControllerExceptionControllerAdvice;

public class ApiResponseData<T> extends ApiResponse {
	
	@SuppressWarnings("rawtypes")
	public static final ApiResponseData NOT_ENOUGH = new ApiResponseData<>(ResultCode.BAD_REQUEST, PARAM_NOT_ENOUGH, null);
	
	@SuppressWarnings("rawtypes")
	public static final ApiResponseData PARAM_ERROR = new ApiResponseData<>(ResultCode.BAD_REQUEST, GlobalControllerExceptionControllerAdvice.PARAMS_ERROR, null);
	
	/**
	 * 数据信息
	 */
	private T data;
	
	public ApiResponseData() {
		this(ResultCode.INTERNAL_SERVER_ERROR, null);
	}
	
	public ApiResponseData(T data) {
		this(ResultCode.OK, data);
	}
	
	public ApiResponseData(ResultCode resultCode, T data) {
		this(resultCode, resultCode.getMessage(), data);
	}

	public ApiResponseData(ResultCode resultCode, String message, T data) {
		this(resultCode.getCode(), message, !(resultCode.getCode() >= 400 && resultCode.getCode() <= 599), data);
	}
	
	public ApiResponseData(int code, String message, boolean status, T data) {
		super(code, message, status);
		this.data = data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}
	
	public T getDataOrElse(T d) {
		return data == null ? d : data ;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> ApiResponseData<E> notEnough() {
		return (ApiResponseData<E>)NOT_ENOUGH;
	}
	
	@SuppressWarnings("unchecked")
	public static final <E> ApiResponseData<E> paramError() {
		return (ApiResponseData<E>)PARAM_ERROR;
	}
}
