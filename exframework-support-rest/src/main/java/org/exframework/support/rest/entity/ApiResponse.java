package org.exframework.support.rest.entity;

import org.exframework.support.rest.enums.ResultCode;

/**
 * Api 通用返回类
 * @author rwe
 *
 * @param
 */
public class ApiResponse {
	
	public static final String PARAM_NOT_ENOUGH = "缺少必要参数";

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
	

	public ApiResponse() {
		this(ResultCode.OK);
	}
	
	public ApiResponse(ResultCode resultCode) {
		this(resultCode, resultCode.getMessage());
	}

	public ApiResponse(ResultCode resultCode, String message) {
		this(resultCode.getCode(), message, !(resultCode.getCode() >= 400 && resultCode.getCode() <= 599));
	}
	
	public ApiResponse(int code, String message, boolean status) {
		this.code = code;
		this.message = message;
		this.status = status;
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
}
