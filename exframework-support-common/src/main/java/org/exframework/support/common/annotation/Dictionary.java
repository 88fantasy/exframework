package org.exframework.support.common.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ANNOTATION_TYPE})
/**
 * 字典
 * @author rwe
 * @since Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface Dictionary {

	String value() default "";
	
	String name() default "";
}
