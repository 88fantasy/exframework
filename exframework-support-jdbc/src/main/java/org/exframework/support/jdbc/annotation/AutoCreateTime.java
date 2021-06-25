package org.exframework.support.jdbc.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE })
/**
 * 自动插入创建时间
 * Author: rwe
 * Date: 2021年5月21日
 *
 * Copyright @ 2021 
 * 
 */
public @interface AutoCreateTime {

}
