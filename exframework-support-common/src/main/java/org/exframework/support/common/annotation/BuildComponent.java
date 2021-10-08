package org.exframework.support.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
* @author rwe
* @version 创建时间：2017年2月14日 下午4:22:30
* 加载此标签后会自动调用build方法
*/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface BuildComponent {

	String value() default "";
}
