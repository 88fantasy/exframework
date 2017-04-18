package com.gzmpc.wf.pojo;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionResult {

	private int status;
	private String errorMessage;
	private Object data;
	
	public OptionResult() {
		this.status = 400;
	}
	
	public OptionResult(int status, String errorMessage, Object data) {
		this.status = status;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public static OptionResult ok(Object data) {
		return new OptionResult(Response.Status.OK.getStatusCode(),null,data);
	}
	
	public static OptionResult error(String errormsg , Object data) {
		return new OptionResult(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),errormsg,data);
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
