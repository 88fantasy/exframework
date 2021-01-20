package com.gzmpc.portal.metadata.module;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.gzmpc.portal.metadata.entity.EntityClass;

@Documented
@Retention(RUNTIME)
@Target({ TYPE })
/**
 * 模块实体
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@EntityClass
public @interface ModuleEntity {

	/**
	 * 参照编码
	 * @return
	 */
	String value();
	
	/**
	 * 参照名称
	 * @return
	 */
	String name();
	
	/**
	 * 参照描述
	 * @return
	 */
	String description() default "";
	
	/**
	 * 引用数据项
	 * @return
	 */
	String[] dataItemRef() default {};
	
	/**
	 * 引用参照
	 * @return
	 */
	String[] hovRef() default {};
	
	/**
	 * 引用权限
	 * @return
	 */
	String[] permissionRef() default {};
	
	boolean forceUpdate() default true;
}