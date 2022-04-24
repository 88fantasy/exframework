package org.exframework.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 网关配置类
 *
 * @author: rwe
 * @version: 1.0
 */
public class GatewayBaseProperties {

    public static final String PREFIX = "gateway";

    /**
     * 网关白名单
     */
    public List<String> gatewayExcludes;

    /**
     * 获取接口验证白名单
     */
    private List<String> apiExcludes;

    /**
     * Xss白名单
     */
    private String xssWhiteUrl;

    /**
     * 失败次数上限
     */
    private Integer blockLimit;

    /**
     * 封禁时间，单位:秒
     */
    private Long blockTime;

    /**
     * 来源过滤
     */
    private List<String> domainFilters;

    /**
     * 限流接口清单
     */
    private List<String> apiRateLimitIncludes;

    /**
     * 每个用户api请求最大次数
     */
    private Integer apiRateLimitRequestMaxCount;

    public List<String> getGatewayExcludes() {
        return gatewayExcludes;
    }

    public GatewayBaseProperties setGatewayExcludes(List<String> gatewayExcludes) {
        this.gatewayExcludes = gatewayExcludes;
        return this;
    }

    public List<String> getApiExcludes() {
        return apiExcludes;
    }

    public GatewayBaseProperties setApiExcludes(List<String> apiExcludes) {
        this.apiExcludes = apiExcludes;
        return this;
    }

    public String getXssWhiteUrl() {
        return xssWhiteUrl;
    }

    public GatewayBaseProperties setXssWhiteUrl(String xssWhiteUrl) {
        this.xssWhiteUrl = xssWhiteUrl;
        return this;
    }

    public Integer getBlockLimit() {
        return blockLimit;
    }

    public GatewayBaseProperties setBlockLimit(Integer blockLimit) {
        this.blockLimit = blockLimit;
        return this;
    }

    public Long getBlockTime() {
        return blockTime;
    }

    public GatewayBaseProperties setBlockTime(Long blockTime) {
        this.blockTime = blockTime;
        return this;
    }

    public List<String> getDomainFilters() {
        return domainFilters;
    }

    public GatewayBaseProperties setDomainFilters(List<String> domainFilters) {
        this.domainFilters = domainFilters;
        return this;
    }

    public List<String> getApiRateLimitIncludes() {
        return apiRateLimitIncludes;
    }

    public GatewayBaseProperties setApiRateLimitIncludes(List<String> apiRateLimitIncludes) {
        this.apiRateLimitIncludes = apiRateLimitIncludes;
        return this;
    }

    public Integer getApiRateLimitRequestMaxCount() {
        return apiRateLimitRequestMaxCount;
    }

    public GatewayBaseProperties setApiRateLimitRequestMaxCount(Integer apiRateLimitRequestMaxCount) {
        this.apiRateLimitRequestMaxCount = apiRateLimitRequestMaxCount;
        return this;
    }
}