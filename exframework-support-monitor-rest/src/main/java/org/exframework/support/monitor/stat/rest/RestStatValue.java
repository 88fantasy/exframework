package org.exframework.support.monitor.stat.rest;

/**
* @author rwe
* @version 创建时间：2018年5月10日 下午1:29:48
* 类说明
*/

public class RestStatValue {

	// 请求次数
	long requestCount;
	
	// 响应次数
	long responseCount;

	public long getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}

	public long getResponseCount() {
		return responseCount;
	}

	public void setResponseCount(long responseCount) {
		this.responseCount = responseCount;
	}

	
}
