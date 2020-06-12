package com.gzmpc.support.sso.core.exception;

/**
 * 自定义异常(CustomException)

 */
public class LoginUserException extends RuntimeException {

    public LoginUserException(String msg){
        super(msg);
    }

    public LoginUserException() {
        super();
    }
}
