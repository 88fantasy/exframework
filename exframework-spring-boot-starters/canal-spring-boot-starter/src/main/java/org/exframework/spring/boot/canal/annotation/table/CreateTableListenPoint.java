package org.exframework.spring.boot.canal.annotation.table;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 表结构发生变化，新增时，先判断数据库实例是否存在，不存在则创建
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.CREATE)
public @interface CreateTableListenPoint {
	
	/**
	 * canal 指令
	 * default for all
	 *
	 * @return canal destination
	 * @author rwe
	 * @time 2018/5/28 15:49
	 * 
	 */
	@AliasFor(annotation = ListenPoint.class)
	String destination() default "";
	
	/**
	 * 数据库实例
	 *
	 * @return canal destination
	 * @author rwe
	 * @time 2018/5/28 15:49
	 * 
	 */
	@AliasFor(annotation = ListenPoint.class)
	String[] database() default {};
}
