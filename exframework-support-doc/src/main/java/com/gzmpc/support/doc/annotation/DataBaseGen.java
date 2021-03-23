package com.gzmpc.support.doc.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Repository;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
/**
 *
 * Author: rwe
 * Date: 2021年3月10日
 *
 * Copyright @ 2021 
 * 
 */
@Repository
public @interface DataBaseGen {

}
