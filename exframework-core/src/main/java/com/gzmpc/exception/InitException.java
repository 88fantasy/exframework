package com.gzmpc.exception;

public class InitException extends BaseException {

	/**
	 * 系统参数等初始化错误
	 */
	private static final long serialVersionUID = 2880810834819603136L;

	
	public InitException() {
		super();
	}
	
	public InitException (String msg) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return "初始化错误["+super.getMessage()+"]";
	}
}
