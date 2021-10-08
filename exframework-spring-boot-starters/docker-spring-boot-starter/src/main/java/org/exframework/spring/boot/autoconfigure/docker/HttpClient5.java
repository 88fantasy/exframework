package org.exframework.spring.boot.autoconfigure.docker;

/**
 * 客户端设置
 *
 * @author rwe
 * @date 2021/9/14 14:03
 **/
public class HttpClient5 {

    private Integer maxConnections;

    private Integer connectionTimeout;

    private Integer responseTimeout;

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public HttpClient5 setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public HttpClient5 setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public Integer getResponseTimeout() {
        return responseTimeout;
    }

    public HttpClient5 setResponseTimeout(Integer responseTimeout) {
        this.responseTimeout = responseTimeout;
        return this;
    }
}
