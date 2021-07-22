package org.exframework.support.common.build;

/**
 * 构建者
 *
 * @author rwe
 * @date 2021/7/22 10:53
 **/
public interface IBuilder<T> {

    /**
     * 构建
     * @return instance
     */
    T build();
}
