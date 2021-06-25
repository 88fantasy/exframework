package org.exframework.support.rest.exception;

import org.exframework.support.rest.enums.ResultCode;

/**
 * Package: org.exframework.support.rest.exception
 *
 * File: ApiException.java 
 *
 * Author: pro   Date: Nov 29, 2020
 *
 * Copyright @ 2020 Corpration Name
 * 400-499 客户端错误
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 4923002775807410211L;

	private int code;
    private String msg;

    public ApiException() {
        this(ResultCode.BAD_REQUEST);
    }
    
    public ApiException(ResultCode code) {
        this(code.getCode(), code.getMessage());
    }

    public ApiException(String msg) {
        this(ResultCode.BAD_REQUEST.getCode(), msg);
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
