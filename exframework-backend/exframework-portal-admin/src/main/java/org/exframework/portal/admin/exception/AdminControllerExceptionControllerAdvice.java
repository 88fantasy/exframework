package org.exframework.portal.admin.exception;

import org.exframework.portal.web.exception.WebControllerExceptionControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 接口错误处理
 * @author rwe
 * @since Jan 8, 2021
 *
 * Copyright @ 2021 
 * 
 */
@RestControllerAdvice( basePackages = "org.exframework.portal.admin.controller")
public class AdminControllerExceptionControllerAdvice extends WebControllerExceptionControllerAdvice {

}
