package com.gzmpc.support.jdbc.exception;
/**
 * 数据库事务错误
 * Author: rwe
 * Date: Jan 6, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class SessionExcetpion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 317097244396539115L;

	public SessionExcetpion() {
		super();
	}
	
	public SessionExcetpion(String msg) {
		super(msg);
	}
	
	public SessionExcetpion(Throwable e) {
		super(e);
	}
}
