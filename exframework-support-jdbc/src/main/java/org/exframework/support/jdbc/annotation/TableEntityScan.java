package org.exframework.support.jdbc.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TableScannerRegistrar.class)
@Repeatable(TableEntityScans.class)
/**
 * 扫描数据库表
 * @author rwe
 * @since Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface TableEntityScan {

	/**
	 * 别名 for basePackages
	 * @return
	 */
	String[] value() default {};
	
	/**
	 * 扫描包体
	 * @return
	 */
	String[] basePackages() default {};

}
