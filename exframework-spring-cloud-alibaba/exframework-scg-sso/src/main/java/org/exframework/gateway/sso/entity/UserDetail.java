package org.exframework.gateway.sso.entity;

/**
 * 用户信息
 *
 * @author rwe
 * @date 2021/11/12 18:04
 **/
public class UserDetail<T> {

    /**
     * 用户主键
     */
    private final String userId;

    /**
     * 用户信息
     */
    private final T data;

    public UserDetail(String userId, T data) {
        this.userId = userId;
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public T getData() {
        return data;
    }
}
