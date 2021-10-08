package org.exframework.portal.metadata.hov;

import org.exframework.portal.metadata.entity.EntityClass;
import org.exframework.portal.pub.PageRequest;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ TYPE })
/**
 *
 * @author rwe
 * @since Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@EntityClass
public @interface HovEntity {

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
	 * 查询参数实体
	 * @return
	 */
	Class<? extends PageRequest> requestEntity();
	
	/**
	 * 返回的字段
	 * @return
	 */
	String returnKey();
	
	/**
	 * 查询数据的实体
	 * @return
	 */
	Class<? extends IHovDao<?>> hovDao();
	
	boolean forceUpdate() default true;
}
