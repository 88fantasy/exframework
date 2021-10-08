package org.exframework.portal.metadata.di;

import org.exframework.portal.enums.DataItemDisplayType;
import org.exframework.portal.enums.DataItemValueType;

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
public @interface DataItemEntity {

	/**
	 * 数据项编码
	 * @return
	 */
	String value();
	
	/**
	 * 数据项名称
	 * @return
	 */
	String name() default "";
	
	/**
	 * 数据项描述
	 * @return
	 */
	String description() default "";
	
	/**
	 * 显示风格
	 * @return
	 */
	DataItemDisplayType type() default DataItemDisplayType.INPUT;
	
	/**
	 * 风格的关键值
	 * @return
	 */
	String displayKey() default "";
	/**
	 * 校证输入值类型
	 * @return
	 */
	DataItemValueType valueType() default DataItemValueType.DEFAULT;
	
	/**
	 * 长度
	 * @return
	 */
	int maxlength() default 0;
	
	/**
	 * 精度
	 * @return
	 */
	int precision() default 0;
	
	/**
	 * 对象码
	 * @return
	 */
	String objectCode() default "";
	
	/**
	 * 代码的默认最高级
	 * @return
	 */
	boolean forceUpdate() default true;
}
