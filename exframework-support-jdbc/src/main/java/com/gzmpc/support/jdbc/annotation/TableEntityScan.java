package com.gzmpc.support.jdbc.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TableScanerRegistrar.class)
@Repeatable(TableEntityScans.class)
/**
 * 扫描数据库表
 * Author: rwe
 * Date: Jan 2, 2021
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
