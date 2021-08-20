package org.exframework.support.rest.enums;

import javax.servlet.http.HttpServletResponse;

/**
 * API 响应码
 * @author rwe
 *
 */
public enum ResultCode {

	OK(HttpServletResponse.SC_OK, "操作成功"),
	CREATE(HttpServletResponse.SC_CREATED, "新建或修改数据成功（Post/Put/patch）"),
	ACCEPTED(HttpServletResponse.SC_ACCEPTED, "已经进入后台排队(异步处理)"),
	NO_CONTENT(HttpServletResponse.SC_NO_CONTENT, "删除数据成功(Delete)"),
	
	BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, "发出的请求有错误，服务器不理解客户端的请求，未做任何处理"),
	UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "没有权限(未登录,未认证)"),
	FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "已登录，但是没有权限,访问被禁止了"),
	NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "所请求的资源不存在，或不可用"),
	METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "HTTP方法错误"),
	NOT_ACCEPTABLE(HttpServletResponse.SC_NOT_ACCEPTABLE, "用户的请求的格式错误"),
	TOO_MANY_REQUESTS(429, "客户端的请求次数超过限额"),
	
	
	INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器处理请求发生错误"),
	BAD_GATEWAY(HttpServletResponse.SC_BAD_GATEWAY, "网关错误"),
	SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "服务不可用,无法处理请求"),
	GATEWAY_TIMEOUT(HttpServletResponse.SC_GATEWAY_TIMEOUT, "网关超时")
	;
	
	
	private int code;
	
	private String message;

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
