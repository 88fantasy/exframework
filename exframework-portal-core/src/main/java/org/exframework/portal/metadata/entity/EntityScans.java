package org.exframework.portal.metadata.entity;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
/**
 * 绑定字典
 * Author: rwe
 * Date: Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface EntityScans {

	/**
	 * 别名 for basePackages
	 * @return
	 */
	EntityScan[] value() default {};

}
