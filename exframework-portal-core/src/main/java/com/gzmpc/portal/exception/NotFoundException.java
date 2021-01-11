package com.gzmpc.portal.exception;

public class NotFoundException extends BaseException {

	/**
	 * 参数缺失等错误
	 */
	private static final long serialVersionUID = 7938287172522133717L;

	public NotFoundException () {
		super();
	}
	
	public NotFoundException ( String msg ) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return "缺失参数错误["+super.getMessage()+"]";
	}
}
