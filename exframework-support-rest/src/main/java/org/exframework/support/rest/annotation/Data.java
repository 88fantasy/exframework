package org.exframework.support.rest.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 *
 * @author rwe
 * @since Nov 29, 2020
 *
 * Copyright @ 2020 
 * 数据返回
 * 自动封装成 org.exframework.support.rest.entity.ApiResponseData<T>>
 */
public @interface Data {

}
