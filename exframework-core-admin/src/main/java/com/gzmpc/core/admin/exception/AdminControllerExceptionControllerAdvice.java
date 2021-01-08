package com.gzmpc.core.admin.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gzmpc.core.web.exception.WebControllerExceptionControllerAdvice;

/**
 * 接口错误处理
 * Author: rwe
 * Date: Jan 8, 2021
 *
 * Copyright @ 2021 
 * 
 */
@RestControllerAdvice( basePackages = "com.gzmpc.core.admin.controller")
public class AdminControllerExceptionControllerAdvice extends WebControllerExceptionControllerAdvice {

}
