package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 转换至产互分页
 *
 * @author rwe
 * @date 2021/7/15 16:46
 **/
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface ChinaUnicomPage {
}
