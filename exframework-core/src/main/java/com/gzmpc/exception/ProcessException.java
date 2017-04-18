package com.gzmpc.exception;

public class ProcessException extends BaseException {

	/**
	 * 统一默认系统运行中的错误
	 */
	private static final long serialVersionUID = 7938287172522133717L;

	public ProcessException () {
		super();
	}
	
	public ProcessException ( String msg ) {
		super(msg);
	}

	@Override
	public String getMessage() {
		return "执行出现错误["+super.getMessage()+"]";
	}
	
	
}
