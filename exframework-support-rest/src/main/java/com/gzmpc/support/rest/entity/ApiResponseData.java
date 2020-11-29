package com.gzmpc.support.rest.entity;

import com.gzmpc.support.rest.enums.ResultCode;

public class ApiResponseData<T> extends ApiResponse {

	/**
	 * 数据信息
	 */
	private T data;
	
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

	public T getData() {
		return data;
	}
}
