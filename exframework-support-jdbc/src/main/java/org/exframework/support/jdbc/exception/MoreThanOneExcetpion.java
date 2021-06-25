package org.exframework.support.jdbc.exception;
/**
 * 数据库事务错误
 * Author: rwe
 * Date: Jan 6, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class MoreThanOneExcetpion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 317097244396539115L;

	public MoreThanOneExcetpion() {
		super();
	}
	
	public MoreThanOneExcetpion(String msg) {
		super(msg);
	}
	
	public MoreThanOneExcetpion(Throwable e) {
		super(e);
	}
}
