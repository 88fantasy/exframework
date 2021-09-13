package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity;

import org.exframework.support.rest.enums.ResultCode;

/**
 * 产互 Api 通用返回类
 * @author rwe
 *
 * @param
 */
public class ChinaUnicomApiResponse {

	public static final String PARAM_NOT_ENOUGH = "缺少必要参数";

	/**
	 * http 状态码
	 */
	private String code;

	/**
	 * 错误信息
	 */
	private String msg;


	public ChinaUnicomApiResponse() {
		this(ResultCode.OK);
	}

	public ChinaUnicomApiResponse(ResultCode resultCode) {
		this(resultCode, resultCode.getMessage());
	}

	public ChinaUnicomApiResponse(ResultCode resultCode, String msg) {
		this(String.valueOf(resultCode.getCode()), msg);
	}

	public ChinaUnicomApiResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
