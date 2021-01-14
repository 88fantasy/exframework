package com.gzmpc.portal.metadata.di;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.gzmpc.portal.metadata.di.DataItem.DataItemDisplayTypeEnum;
import com.gzmpc.portal.metadata.di.DataItem.DataItemValueTypeEnum;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE})
/**
 *
 * Author: rwe
 * Date: Jan 11, 2021
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
	DataItemDisplayTypeEnum type() default DataItemDisplayTypeEnum.INPUT;
	
	/**
	 * 风格的关键值
	 * @return
	 */
	String displayKey() default "";
	/**
	 * 校证输入值类型
	 * @return
	 */
	DataItemValueTypeEnum valueType() default DataItemValueTypeEnum.DEFAULT;
	
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
