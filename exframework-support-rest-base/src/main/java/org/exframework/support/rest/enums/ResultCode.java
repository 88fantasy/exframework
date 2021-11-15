package org.exframework.support.rest.enums;

import org.springframework.http.HttpStatus;

/**
 * API 响应码
 * @author rwe
 *
 */
public enum ResultCode {

	OK(HttpStatus.OK.value(), "操作成功"),
	CREATE(HttpStatus.CREATED.value(), "新建或修改数据成功（Post/Put/patch）"),
	ACCEPTED(HttpStatus.ACCEPTED.value(), "已经进入后台排队(异步处理)"),
	NO_CONTENT(204, "删除数据成功(Delete)"),

	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "发出的请求有错误，服务器不理解客户端的请求，未做任何处理"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "没有权限(未登录,未认证)"),
	FORBIDDEN(HttpStatus.FORBIDDEN.value(), "已登录，但是没有权限,访问被禁止了"),
	NOT_FOUND(HttpStatus.NOT_FOUND.value(), "所请求的资源不存在，或不可用"),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "HTTP方法错误"),
	NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE.value(), "用户的请求的格式错误"),
	TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS.value(), "客户端的请求次数超过限额"),


	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器处理请求发生错误"),
	BAD_GATEWAY(HttpStatus.BAD_GATEWAY.value(), "网关错误"),
	SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), "服务不可用,无法处理请求"),
	GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT.value(), "网关超时")
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
