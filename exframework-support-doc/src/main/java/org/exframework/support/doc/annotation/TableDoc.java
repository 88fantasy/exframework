package org.exframework.support.doc.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
/**
 * 表相关 文档注解
 * @author rwe
 * @since 2021年3月10日
 *
 * Copyright @ 2021 
 * 
 */
@Inherited
public @interface TableDoc {

	/**
	 * 描述
	 * @return
	 */
	String value();
	
}
