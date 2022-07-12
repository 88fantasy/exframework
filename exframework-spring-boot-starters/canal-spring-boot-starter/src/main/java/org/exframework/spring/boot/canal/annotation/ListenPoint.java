package org.exframework.spring.boot.canal.annotation;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.lang.annotation.*;

/**
 * 监听数据库的操作
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListenPoint {
	
	/**
	 * canal 指令
	 * default for all
	 * 支持 spel 表达式 附带 env 参数
	 * sample 'pre'.equals(#env.getActiveProfiles()[0])? 'uniresearch-pre' : 'uniresearch'
	 * @see org.springframework.core.env.Environment
	 *
	 * @return canal destination
	 * 
	 */
	String destination() default "";
	
	/**
	 * 数据库实例
	 *
	 * @return canal destination
	 * 
	 */
	String[] database() default {};
	
	/**
	 * 监听的表
	 * default for all
	 *
	 * @return canal destination
	 * 
	 */
	String[] table() default {};
	
	/**
	 * 监听操作的类型
	 * <p>
	 * default for all
	 *
	 * @return canal event type
	 * 
	 */
	CanalEntry.EventType[] eventType() default {};
	
}
