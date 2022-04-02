package org.exframework.spring.boot.canal.annotation.content;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 更新操作监听器
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.UPDATE)
public @interface UpdateListenPoint {

    /**
     * canal 指令
     * default for all
     *
     * @return canal destination
     * 
     */
    @AliasFor(annotation = ListenPoint.class)
    String destination() default "";

    /**
     * 数据库实例
     *
     * @return canal destination
     * 
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] database() default {};

    /**
     * 监听的表
     * default for all
     *
     * @return canal destination
     * 
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};

}
