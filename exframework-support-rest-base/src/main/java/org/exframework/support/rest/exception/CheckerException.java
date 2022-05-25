package org.exframework.support.rest.exception;

import org.exframework.support.rest.enums.ResultCode;

import javax.validation.ConstraintDeclarationException;

/**
 * Package: org.exframework.support.rest.exception
 * <p>
 * File: CheckerException.java
 *
 * @author pro   Date: Nov 29, 2020
 * <p>
 * 400-499 客户端错误
 */
public class CheckerException extends ConstraintDeclarationException {

    private int code;
    private String msg;
    private Object data;

    public CheckerException() {
        this(ResultCode.BAD_REQUEST);
    }

    public CheckerException(ResultCode code) {
        this(code.getCode(), code.getMessage());
    }

    public CheckerException(String msg) {
        this(ResultCode.BAD_REQUEST.getCode(), msg);
    }

    public CheckerException(int code, String msg) {
        this(code, msg, null);
    }

    public CheckerException(int code, String msg, Object data) {
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
