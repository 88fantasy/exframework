package org.exframework.support.doc.annotation;

import org.exframework.support.doc.entity.DataBaseTableSource;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE })
/**
 * 表字段相关 文档注解
 * @author rwe
 * @since 2021年3月21日
 *
 * Copyright @ 2021 
 * 
 */
@Inherited
public @interface TableFieldDoc {

	/**
	 * 字段描述
	 * @return
	 */
	String value();
	
	/**
	 * 默认值
	 * @return
	 */
	String defaultValue() default DataBaseTableSource.DEFAUTL_VALUE;
}
