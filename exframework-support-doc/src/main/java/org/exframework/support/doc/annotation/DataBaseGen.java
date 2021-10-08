package org.exframework.support.doc.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
/**
 *
 * @author rwe
 * @since 2021年3月10日
 *
 * Copyright @ 2021 
 * 
 */
@Repository
public @interface DataBaseGen {

}
