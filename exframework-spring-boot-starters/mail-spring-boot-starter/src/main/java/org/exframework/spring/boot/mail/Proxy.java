package org.exframework.spring.boot.mail;


/**
 * 邮件socks5 代理
 * @author rwe
 * @since 2021年5月25日
 *
 * Copyright @ 2021 
 * 
 */
public class Proxy {
	
    private String host;

    private Integer port;

    private String username;

    private String password;

    public String getHost() {
        return host;
    }

    public Proxy setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public Proxy setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Proxy setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Proxy setPassword(String password) {
        this.password = password;
        return this;
    }
}