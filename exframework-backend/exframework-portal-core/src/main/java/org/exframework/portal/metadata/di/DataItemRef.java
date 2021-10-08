package org.exframework.portal.metadata.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE})
/**
 *
 * @author rwe
 * @since Jan 11, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface DataItemRef {

	/**
	 * 数据项编码
	 * @return
	 */
	String value();
	
	/**
	 * 扩展编码
	 * @return
	 */
	String objectCode() default "";
}
