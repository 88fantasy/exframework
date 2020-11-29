package com.gzmpc.support.rest.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 *
 * Author: rwe
 * Date: Nov 29, 2020
 *
 * Copyright @ 2020 
 * 数据返回
 * 自动封装成 com.gzmpc.support.rest.entity.ApiResponseData<T>>
 */
public @interface Data {

}
