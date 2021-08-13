package org.exframework.support.jdbc.exception;
/**
 * 数据库事务错误
 * @author rwe
 * @since Jan 6, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class MoreThanOneException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 317097244396539115L;

	public MoreThanOneException() {
		super();
	}
	
	public MoreThanOneException(String msg) {
		super(msg);
	}
	
	public MoreThanOneException(Throwable e) {
		super(e);
	}
}
