package com.gzmpc.support.rest.enums;

/**
 * API 响应码
 * @author rwe
 *
 */
public enum ResultCode {

	OK(200, "操作成功"),
	CREATE(201, "新建或修改数据成功（Post/Put/patch）"),
	ACCEPTED(202, "已经进入后台排队(异步处理)"),
	NO_CONTENT(204, "删除数据成功(Delete)"),
	
	BAD_REQUEST(400, "发出的请求有错误，服务器不理解客户端的请求，未做任何处理"),
	UNAUTHORIZED(401, "没有权限(未登录,未认证)"),
	FORBIDDEN(403, "已登录，但是没有权限,访问被禁止了"),
	NOT_FOUND(404, "所请求的资源不存在，或不可用"),
	METHOD_NOT_ALLOWED(405, "HTTP方法错误"),
	NOT_ACCEPTABLE(406, "用户的请求的格式错误(content-type)"),
	TOO_MANY_REQUESTS(429, "客户端的请求次数超过限额"),
	
	
	INTERNAL_SERVER_ERROR(500, "服务器处理请求发生错误"),
	BAD_GATEWAY(502, "网关错误"),
	SERVICE_UNAVAILABLE(503, "服务不可用,无法处理请求"),
	GATEWAY_TIMEOUT(504, "网关超时")
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
