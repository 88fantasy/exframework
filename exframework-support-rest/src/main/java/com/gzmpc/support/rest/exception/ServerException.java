package com.gzmpc.support.rest.exception;

import com.gzmpc.support.rest.enums.ResultCode;

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
		super(ResultCode.INTERNAL_SERVER_ERROR);
	}
}
