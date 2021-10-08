package org.exframework.support.jdbc.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
* @author rwe
* @version 创建时间：2017年2月14日 下午4:22:30
* 加载此标签后会自动调用getConnection方法
* 需要继承 org.exframework.support.jdbc.dao.DbDao
*/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface DbInit {

	String value() default "";
}
