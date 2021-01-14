package com.gzmpc.portal.metadata.hov;

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
 *
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface HovRef {

	String value();
	
}
