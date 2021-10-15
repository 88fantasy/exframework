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
 */

public @interface ThreadData {


    class ThreadDataClass {

        private static final ThreadLocal<Map<String, Object>> data = InheritableThreadLocal
                .withInitial(() -> new ConcurrentHashMap<>(8));


        public static void clear() {
            if (data.get() != null) {
                data.get().clear();
            }
            data.remove();
        }

        public static void bind(String key, Object value) {
            if (key == null) {
                throw new NullPointerException("key cannot be null");
            } else if (value == null) {
                data.get().remove(key);
            } else {
                data.get().put(key, value);
            }
        }

        public static Object getBind(String key) {
            return data.get().get(key);
        }

        public static Map<String, Object> get() {
            return data.get();
        }
    }
}
