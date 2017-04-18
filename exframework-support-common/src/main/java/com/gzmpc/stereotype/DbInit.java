package com.gzmpc.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Repository;

/**
* @author rwe
* @version 创建时间：2017年2月14日 下午4:22:30
* 加载此标签后会自动调用getConnection方法
*/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface DbInit {

	String value() default "";
}
