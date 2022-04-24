package org.exframework.gateway.sso.exception;

/**
 * 密码错误
 *
 * @author rwe
 * @date 2021/11/11 00:10
 **/
public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        //依据安全问题要求模糊描述
        super("帐号或密码错误");
    }

    public NotAuthException(String message) {
        super(message);
    }
}
