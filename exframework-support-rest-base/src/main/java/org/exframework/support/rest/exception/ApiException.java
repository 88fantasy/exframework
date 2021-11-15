package org.exframework.support.rest.exception;

import org.exframework.support.rest.enums.ResultCode;

/**
 * Package: org.exframework.support.rest.exception
 * <p>
 * File: ApiException.java
 *
 * @author pro   Date: Nov 29, 2020
 * <p>
 * Copyright @ 2020 Corpration Name
 * 400-499 客户端错误
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 4923002775807410211L;

    private int code;
    private String msg;
    private Object data;

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
        this(code, msg, null);
    }

    public ApiException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
