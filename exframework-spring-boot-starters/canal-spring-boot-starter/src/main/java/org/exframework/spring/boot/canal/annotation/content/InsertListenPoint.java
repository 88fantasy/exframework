package org.exframework.spring.boot.canal.annotation.content;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
/**
 * 新增操作监听器
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.INSERT)
public @interface InsertListenPoint {

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

    /**
     * 监听的表
     * default for all
     *
     * @return canal destination
     * @author rwe
     * @time 2018/5/28 15:50
     * 
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};

}
