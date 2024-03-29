package org.exframework.support.rest.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.rest.enums.ResultCode;

public class ApiResponsePage<T> extends ApiResponse {
	
	@SuppressWarnings("rawtypes")
	public static final ApiResponsePage NOT_ENOUGH = new ApiResponsePage<>(ResultCode.BAD_REQUEST, PARAM_NOT_ENOUGH, null);
	
	@SuppressWarnings("rawtypes")
	public static final ApiResponsePage PARAM_ERROR = new ApiResponsePage<>(ResultCode.BAD_REQUEST, PARAMS_ERROR, null);

	@SuppressWarnings("rawtypes")
	public static final ApiResponsePage EMPTY = new ApiResponsePage<>(PageModel.EMPTY);
	
	/**
	 * 分页数据
	 */
	private PageModel<T> data;
	
	public ApiResponsePage() {
		this(ResultCode.INTERNAL_SERVER_ERROR, null);
	}
	
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


	public void setData(PageModel<T> data) {
		this.data = data;
	}

	public PageModel<T> getData() {
		return data;
	}
	
	public PageModel<T> getDataOrElse(PageModel<T> d) {
		return data == null ? d : data ;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> ApiResponsePage<E> notEnough() {
		return (ApiResponsePage<E>)NOT_ENOUGH;
	}
	
	@SuppressWarnings("unchecked")
	public static  <E> ApiResponsePage<E> paramError() {
		return (ApiResponsePage<E>)PARAM_ERROR;
	}
	
	public static <E> ApiResponsePage<E> notFound(String message) {
		return new ApiResponsePage<E>(ResultCode.NOT_FOUND, message, null);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> ApiResponsePage<E> empltyPage() {
		return (ApiResponsePage<E>)EMPTY;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "ApiResponse{" +
					"data='" + data +
					'}';
		}

	}
}
