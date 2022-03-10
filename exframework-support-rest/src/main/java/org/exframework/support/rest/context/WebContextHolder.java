package org.exframework.support.rest.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口上下文
 *
 * @author rwe
 * @date 2022/3/8 16:59
 **/
public class WebContextHolder {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = InheritableThreadLocal
            .withInitial(() -> new ConcurrentHashMap<>(32));

    public static void clear() {
        if (CONTEXT.get() != null) {
            CONTEXT.get().clear();
        }
        CONTEXT.remove();
    }

    public static void bind(String key, Object value) {
        if (key == null) {
            throw new NullPointerException("key cannot be null");
        } else if (value == null) {
            CONTEXT.get().remove(key);
        } else {
            CONTEXT.get().put(key, value);
        }
    }

    public static Object getBind(String key) {
        return CONTEXT.get().get(key);
    }

    public static Map<String, Object> get() {
        return CONTEXT.get();
    }
}
