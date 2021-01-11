package com.gzmpc.portal.exception;

@SuppressWarnings("serial")
public class BaseException extends RuntimeException {

	public BaseException() {
		super();
	}
	
	public BaseException(String msg) {
		super(msg);
	}
	
	public BaseException(Throwable e) {
		super(e);
	}
}
