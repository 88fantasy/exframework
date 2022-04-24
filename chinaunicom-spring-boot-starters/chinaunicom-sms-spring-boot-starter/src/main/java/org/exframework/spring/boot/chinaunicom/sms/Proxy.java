package org.exframework.spring.boot.chinaunicom.sms;

import org.springframework.lang.Nullable;

/**
 * 代理配置
 *
 * @author rwe
 * @date 2021/8/13 09:00
 **/
public class Proxy {

    /**
     * 代理地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    @Nullable
    private String username;

    /**
     * 密码
     */
    @Nullable
    private String password;

    public String getHost() {
        return host;
    }

    public Proxy setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPort() {
        return port;
    }

    public Proxy setPort(String port) {
        this.port = port;
        return this;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public Proxy setUsername(@Nullable String username) {
        this.username = username;
        return this;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public Proxy setPassword(@Nullable String password) {
        this.password = password;
        return this;
    }
}
