package org.exframework.spring.boot.autoconfigure.docker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

import static org.exframework.spring.boot.autoconfigure.docker.DockerProperties.DEFAULT_PREFIX;

/**
 * minio配置类
 *
 * @author rwe
 * @since 2021年5月20日
 * <p>
 * Copyright @ 2021
 */
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class DockerProperties {

    /**
     * PREFIX
     */
    public static final String DEFAULT_PREFIX = "docker";

    /**
     * 服务器地址
     */
    private String host;

    /**
     * 仓库设置
     */
    private Registry registry;

    /**
     * 客户端设置
     */
    @Nullable
    private HttpClient5 httpClient5;


    public String getHost() {
        return host;
    }

    public DockerProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public Registry getRegistry() {
        return registry;
    }

    public DockerProperties setRegistry(Registry registry) {
        this.registry = registry;
        return this;
    }

    @Nullable
    public HttpClient5 getHttpClient5() {
        return httpClient5;
    }

    public DockerProperties setHttpClient5(@Nullable HttpClient5 httpClient5) {
        this.httpClient5 = httpClient5;
        return this;
    }

}
