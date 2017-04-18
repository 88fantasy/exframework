package com.gzmpc.exception;

public class NotAuthorizedException extends BaseException {

	/**
	 * 登录失败错误
	 */
	private static final long serialVersionUID = -4704717066150172638L;

	public NotAuthorizedException() {
		super();
	}
	
	public NotAuthorizedException(String msg) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return "权限校验错误["+super.getMessage()+"]";
	}
}
