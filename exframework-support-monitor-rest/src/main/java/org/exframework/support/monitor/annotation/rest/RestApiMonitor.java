package org.exframework.support.monitor.annotation.rest;

import java.lang.annotation.*;

/**
* @author rwe
* @version 创建时间：2018年5月13日 下午1:32:32
* 类说明
*/

@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface RestApiMonitor {

	/** 
	 * 要执行的操作类型 
	 **/  
	public String type() default "";  
	
	/** 
	 * 要执行的具体描述 
	 **/  
	public String desc() default "";
}
