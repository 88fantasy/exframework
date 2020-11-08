package com.gzmpc.support.common.entity;

import com.gzmpc.support.common.enums.ResultCode;

public class ApiResponsePage<T> extends ApiResponse {


	private PageModel<T> data;
	

	public ApiResponsePage(PageModel<T> data) {
		this(ResultCode.OK, data);
	}
	
	public ApiResponsePage(ResultCode resultCode, PageModel<T> data) {
		this(resultCode, resultCode.getMessage(), data);
	}

	public ApiResponsePage(ResultCode resultCode, String message, PageModel<T> data) {
		this(resultCode.getCode(), message, !(resultCode.getCode() >= 400 && resultCode.getCode() <= 599), data);
	}
	
	public ApiResponsePage(int code, String message, boolean status, PageModel<T> data) {
		super(code, message, status);
		this.data = data;
	}


	public PageModel<T> getData() {
		return data;
	}
	
	
}
