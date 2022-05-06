package org.exframework.support.jdbc.listener;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Hashtable;

/**
 * 类说明
 *
 * @author rwe
 * @version 1.0
 * @date 创建时间：2021年5月21日 下午5:02:36
 */

public class PrimaryMetaObjectHandler implements MetaObjectHandler {

    private final Hashtable<String, MetaObjectHandler> handlers = new Hashtable<>();

    @Override
    public void insertFill(MetaObject metaObject) {
        handlers.values().forEach(o -> o.insertFill(metaObject));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        handlers.values().forEach(o -> o.updateFill(metaObject));
    }

    public PrimaryMetaObjectHandler register(MetaObjectHandler handler) {
        handlers.put(handler.getClass().getName(), handler);
        return this;
    }

}
