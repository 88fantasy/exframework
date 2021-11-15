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

	public static final String PARAMS_ERROR = "参数校验错误";

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
		this(resultCode.getCode(), message, statusFromCode(resultCode.getCode()));
	}
	
	public ApiResponse(int code, String message, boolean status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}

	public static boolean statusFromCode(int code) {
		return !(code >= 400 && code <= 599);
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