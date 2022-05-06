package org.exframework.support.common.entity;

import java.util.Collection;

/**
 * 字段剔除
 *
 * @author rwe
 * @date 2022/4/26 19:24
 **/
public interface RequestIgnoreList {

    Collection<String> ignoreList();
}
