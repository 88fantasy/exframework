package com.gzmpc.support.common.exception;

/**
 * API 错误类
 * 
 * @author rwe
 *
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 4474666437264484249L;

	private int code;

	private String message;

	public ApiException(String message) {
		this(500, message);
	}

	public ApiException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
