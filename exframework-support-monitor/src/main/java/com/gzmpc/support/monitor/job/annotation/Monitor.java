package com.gzmpc.support.monitor.job.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
* @author rwe
* @version 创建时间：2017年2月14日 下午4:22:30
* 加载此标签后会自动加载至 <{@link com.gzmpc.support.monitor.stat.StatManager}>
*/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Monitor {

	String value() default "";
}
