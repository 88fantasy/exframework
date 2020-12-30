package com.gzmpc.metadata.dict;

import java.lang.annotation.*;

/**
* @author rwe
* @version 创建时间：Oct 15, 2020 11:07:15 AM
* 字典引用(上传配置)
*/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dictionary {

	/**
	 * 配置项key
	 * @return
	 */
	String key() default "";
	
	/**
	 * 关联枚举
	 * @return
	 */
//	Class<? extends DictionaryEnum> enums() default DictionaryEnum.class;
	/**
	 * 是否同步 单位/秒
	 * -1 不同步
	 * to-do 0采用推送的方式
	 * @return
	 */
	 long sync() default -1;
}
