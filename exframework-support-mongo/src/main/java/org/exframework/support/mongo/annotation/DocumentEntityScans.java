package org.exframework.support.mongo.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
/**
 * 扫描数据库表
 * @author rwe
 * @since Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface DocumentEntityScans {

	/**
	 * 别名 for basePackages
	 * @return
	 */
	DocumentEntityScan[] value() default {};

}
