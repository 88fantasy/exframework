package com.gzmpc.tsf.support.cos.exception;

public class TencentCOSException extends Exception {

	
	private static final long serialVersionUID = 8355209447119060072L;
	

	public TencentCOSException() {}
	
	public TencentCOSException(String msg) {
		super(msg);
	}
}
