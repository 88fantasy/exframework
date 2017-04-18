package com.gzmpc.exception;

@SuppressWarnings("serial")
public class BaseException extends Exception {

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
