package org.exframework.portal.metadata.entity;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
/**
 * 绑定字典
 * @author rwe
 * @since Jan 2, 2021
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
