package org.exframework.spring.boot.canal.annotation;

import org.exframework.spring.boot.canal.config.CanalClientConfiguration;
import org.exframework.spring.boot.canal.config.CanalProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 Canal 客户端
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CanalProperties.class, CanalClientConfiguration.class})
public @interface EnableCanalClient {
}