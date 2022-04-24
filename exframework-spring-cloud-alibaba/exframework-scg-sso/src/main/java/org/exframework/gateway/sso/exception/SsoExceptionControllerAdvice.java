package org.exframework.gateway.sso.exception;

import cn.dev33.satoken.exception.NotLoginException;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.exframework.support.rest.exception.GlobalControllerExceptionControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;

/**
 * @author rwe
 * @since Nov 29, 2020
 * <p>
 * Copyright @ 2020
 * 接口全局处理
 */
@RestControllerAdvice
@Order(-99)
public class SsoExceptionControllerAdvice extends GlobalControllerExceptionControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(SsoExceptionControllerAdvice.class.getName());

    public static final String PARAMS_ERROR = "参数校验错误";


    @ExceptionHandler(value = {NotAuthException.class, NotLoginException.class})
    public ApiResponseData<Object> notAuthException(Exception e) {
        errorHandle(e);
        return new ApiResponseData<>(ResultCode.UNAUTHORIZED, e.getMessage(), null);
    }

    private void errorHandle(Exception e) {
        logger.error(MessageFormat.format("全局错误{0}处理:{1}", this.getClass().getName(), e.getMessage()), e);
    }
}
