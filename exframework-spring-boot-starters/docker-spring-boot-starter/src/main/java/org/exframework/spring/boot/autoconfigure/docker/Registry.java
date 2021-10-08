package org.exframework.spring.boot.autoconfigure.docker;

import org.springframework.lang.Nullable;

/**
 * 仓库
 *
 * @author rwe
 * @date 2021/9/14 14:00
 **/
public class Registry {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    @Nullable
    private String email;

    /**
     * 仓库地址
     */
    @Nullable
    private String url;

    public String getUsername() {
        return username;
    }

    public Registry setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Registry setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Registry setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Registry setUrl(String url) {
        this.url = url;
        return this;
    }
}
