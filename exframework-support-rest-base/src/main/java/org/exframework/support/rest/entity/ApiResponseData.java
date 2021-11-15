package org.exframework.support.rest.entity;


import org.exframework.support.rest.enums.ResultCode;

public class ApiResponseData<T> extends ApiResponse {
	
	@SuppressWarnings("rawtypes")
	public static final ApiResponseData NOT_ENOUGH = new ApiResponseData<>(ResultCode.BAD_REQUEST, PARAM_NOT_ENOUGH, null);
	
	@SuppressWarnings("rawtypes")
	public static final ApiResponseData PARAM_ERROR = new ApiResponseData<>(ResultCode.BAD_REQUEST, PARAMS_ERROR, null);
	
	@SuppressWarnings("rawtypes")
	public static final ApiResponseData EMPTY = new ApiResponseData<>(null);
	
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
		return data == null || !isStatus() ? d : data ;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> ApiResponseData<E> notEnough() {
		return (ApiResponseData<E>)NOT_ENOUGH;
	}
	
	@SuppressWarnings("unchecked")
	public static final <E> ApiResponseData<E> paramError() {
		return (ApiResponseData<E>)PARAM_ERROR;
	}
	
	public static final <E> ApiResponseData<E> notFound(String message) {
		return new ApiResponseData<E>(ResultCode.NOT_FOUND, message, null);
	}
	
	@SuppressWarnings("unchecked")
	public static final <E> ApiResponseData<E> empltyData() {
		return (ApiResponseData<E>)EMPTY;
	}
}
