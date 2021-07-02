package org.exframework.support.rest.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exframework.support.rest.annotation.Data;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.exception.ApiException;

/**
 *
 * @author rwe
 * @since Nov 29, 2020
 *
 * Copyright @ 2020 
 * 全局封装返回值类
 */
@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethodAnnotation(Data.class) != null;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ApiResponseData里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ApiResponseData<>(body));
            } catch (JsonProcessingException e) {
                throw new ApiException("返回String类型错误");
            }
        }
        return new ApiResponseData<>(body);
	}

}
