package org.exframework.portal.admin.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.exframework.portal.web.exception.WebControllerExceptionControllerAdvice;

/**
 * 接口错误处理
 * Author: rwe
 * Date: Jan 8, 2021
 *
 * Copyright @ 2021 
 * 
 */
@RestControllerAdvice( basePackages = "org.exframework.portal.admin.controller")
public class AdminControllerExceptionControllerAdvice extends WebControllerExceptionControllerAdvice {

}
