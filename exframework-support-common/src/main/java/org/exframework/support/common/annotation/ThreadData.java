package org.exframework.support.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target({METHOD, ANNOTATION_TYPE})
@Documented
@Inherited
/**
 * @author rwe
 * @version 创建时间：2021年5月21日 下午4:42:56
 * 访问变量
 *
 * Note: org.exframework.support.common.annotation.ThreadData.ThreadDataClass 的链路中必须声明 @ThreadData
 *
 */

public @interface ThreadData {


    /**
     * ThreadData 专用变量
     * 使用 ThreadDataClass的链路前必须使用 @ThreadData 注解
     */
    class ThreadDataClass {

        private static final ThreadLocal<Map<String, Object>> DATA = InheritableThreadLocal
                .withInitial(() -> new ConcurrentHashMap<>(32));


        public static void clear() {
            if (DATA.get() != null) {
                DATA.get().clear();
            }
            DATA.remove();
        }

        public static void bind(String key, Object value) {
            if (key == null) {
                throw new NullPointerException("key cannot be null");
            } else if (value == null) {
                DATA.get().remove(key);
            } else {
                DATA.get().put(key, value);
            }
        }

        public static Object getBind(String key) {
            return DATA.get().get(key);
        }

        public static Map<String, Object> get() {
            return DATA.get();
        }
    }
}
