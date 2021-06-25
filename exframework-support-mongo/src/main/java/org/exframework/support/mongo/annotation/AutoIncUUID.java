package org.exframework.support.mongo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
* @author rwe
* @version 创建时间：May 31, 2020 12:10:41 PM
* uuid  string类型自增
*/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIncUUID {

}
