package org.exframework.support.jdbc.annotation;

import javax.lang.model.type.NullType;
import java.lang.annotation.*;

/**
 * 查询条件注解
 *
 * @author rwe
 * @date 2022/7/3 23:04
 **/
@Target(ElementType.FIELD)
@Repeatable(Query.List.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Query {

    /**
     * 查询条件
     */
    Condition condition() default Condition.EQ;

    /**
     * 数据库字段，默认为空，自动根据驼峰转下划线
     */
    String field() default "";

    /**
     * 绑定的Entity类
     */
    Class<?> entity() default NullType.class;

    /**
     * JOIN连接条件，支持动态的跨表JOIN查询
     */
    String join() default "";

    /**
     * 忽略该字段
     */
    boolean ignore() default false;


    /**
     * 在同一个字段上支持多个{@link Query}，之间会用采用OR的方式连接
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Query[] value();
    }

    enum Condition {
        /**
         * 等于
         */
        EQ,

        /**
         * 包含
         */
        IN,

        /**
         * 以xx开始
         */
        STARTSWITH,

        /**
         * 以xx结束
         */
        ENDSWITH,

        /**
         * 匹配
         */
        LIKE,

        /**
         * 大于
         */
        GT,

        /**
         * 大于等于
         */
        GE,

        /**
         * 小于
         */
        LT,

        /**
         * 小于等于
         */
        LE,

        /**
         * 介于
         */
        BETWEEN,

        /**
         * 不等于
         */
        NE


    }
}
