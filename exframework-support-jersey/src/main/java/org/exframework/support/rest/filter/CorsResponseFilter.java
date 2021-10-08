package org.exframework.support.rest.filter;

import org.springframework.http.HttpStatus;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;


/**
* @author rwe
* @version 创建时间：Feb 14, 2020 1:27:12 PM
* 跨域解决办法
*/

public class CorsResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		//跨域请求，*代表允许全部类型
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		//允许请求方式
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		//用来指定本次预检请求的有效期，单位为秒，在此期间不用发出另一条预检请求
		responseContext.getHeaders().add("Access-Control-Max-Age", "3600");
		//请求包含的字段内容，如有多个可用哪个逗号分隔如下
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "content-type,x-requested-with,Authorization, x-ui-request,lang");
		//访问控制允许凭据，true为允许
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		// 浏览器是会先发一次options请求，如果请求通过，则继续发送正式的post请求
        // 配置options的请求返回
		if ("OPTIONS".equals(requestContext.getMethod())) {
			responseContext.setStatus(HttpStatus.OK.value());
		}
	}

}
