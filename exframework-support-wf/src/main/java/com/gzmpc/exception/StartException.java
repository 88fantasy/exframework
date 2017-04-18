package com.gzmpc.exception;

import com.gzmpc.exception.BaseException;

@SuppressWarnings("serial")
public class StartException extends BaseException {

	public StartException() {
		super();
	}
	
	public StartException(String msg) {
		super(msg);
	}
	
	public StartException(Throwable e) {
		super(e);
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "提交流程失败:"+super.getMessage();
	}
}
