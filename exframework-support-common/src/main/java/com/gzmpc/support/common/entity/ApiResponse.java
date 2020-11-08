package com.gzmpc.support.common.entity;

import com.gzmpc.support.common.enums.ResultCode;

/**
 * Api 通用返回类
 * @author rwe
 *
 * @param <T>
 */
public class ApiResponse<T> {

	/**
	 * http 状态码
	 */
	private int code;
	
	/**
	 * 错误信息 if status is false
	 */
	private String message;
	
	/**
	 * 快速进行判断
	 * HTTP状态响应码在400-499或500-599之间为 false
	 */
	private boolean status;
	
	/**
	 * 数据信息
	 */
	private T data;
	
	

	public ApiResponse(T data) {
		this(ResultCode.OK, data);
	}
	
	public ApiResponse(ResultCode resultCode, T data) {
		this(resultCode, resultCode.getMessage(), data);
	}

	public ApiResponse(ResultCode resultCode, String message, T data) {
		this(resultCode.getCode(), message, !(resultCode.getCode() >= 400 && resultCode.getCode() <= 599), data);
	}
	
	public ApiResponse(int code, String message, boolean status, T data) {
		this.code = code;
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public boolean isStatus() {
		return status;
	}

	public T getData() {
		return data;
	}
	
}
