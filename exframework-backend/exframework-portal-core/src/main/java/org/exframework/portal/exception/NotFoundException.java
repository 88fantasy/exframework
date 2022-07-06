package org.exframework.portal.exception;

public class NotFoundException extends BaseException {

	/**
	 * 参数缺失等错误
	 */
	private static final long serialVersionUID = 7938287172522133717L;

	public NotFoundException () {
		super("未找到");
	}
	
	public NotFoundException ( String msg ) {
		super(msg);
	}


}
