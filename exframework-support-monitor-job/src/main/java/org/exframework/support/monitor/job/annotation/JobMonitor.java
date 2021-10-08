package org.exframework.support.monitor.job.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
* @author rwe
* @version 创建时间：2017年2月14日 下午4:22:30
* 加载此标签后会自动加载至 <{@link org.exframework.support.monitor.stat.StatManager}>
*/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface JobMonitor {

	String value() default "";
}
