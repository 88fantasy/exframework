package com.gzmpc.portal.admin.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gzmpc.portal.web.exception.WebControllerExceptionControllerAdvice;

/**
 * 接口错误处理
 * Author: rwe
 * Date: Jan 8, 2021
 *
 * Copyright @ 2021 
 * 
 */
@RestControllerAdvice( basePackages = "com.gzmpc.portal.admin.controller")
public class AdminControllerExceptionControllerAdvice extends WebControllerExceptionControllerAdvice {

}
