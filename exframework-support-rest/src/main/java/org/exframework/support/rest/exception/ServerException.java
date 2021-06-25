package org.exframework.support.rest.exception;

import org.exframework.support.rest.enums.ResultCode;

/**
 *
 * Author: rwe
 * Date: Nov 29, 2020
 *
 * Copyright @ 2020 
 * 500-599 服务器端错误
 */
public class ServerException extends ApiException {

	private static final long serialVersionUID = -2886076214889795949L;

	public ServerException() {
		this(ResultCode.INTERNAL_SERVER_ERROR);
	}

	public ServerException(int code, String msg) {
		super(code, msg);
	}

	public ServerException(ResultCode code) {
		super(code);
	}

	public ServerException(String msg) {
		super(msg);
	}
	
	
}
