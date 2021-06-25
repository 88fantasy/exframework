package org.exframework.support.wechat.exception;

/**
* @author rwe
* @version 创建时间：Jun 15, 2020 12:59:26 PM
* api 调用错误类
*/

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -3173241984108694320L;

	public ApiException() {
		super();
	}
	
	public ApiException(String msg) {
		super(msg);
	}
	
	public ApiException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public ApiException(Throwable e) {
		super(e);
	}
}
