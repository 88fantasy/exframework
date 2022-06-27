package org.exframework.portal.metadata.dict;

import org.springframework.core.Ordered;

import java.util.Collection;
import java.util.List;

/**
 * @author rwe
 * @date 2022/6/26 22:13
 **/
public interface DictionaryDataSource extends Ordered {

    /**
     * 数据来源类型
     * @return
     */
    String dataSourceType();

    /**
     * 字典项集合
     * @return
     */
    Collection<String> keys();

    /**
     * 获取字典项的值
     * @param key
     * @return
     */
    List<DictionaryItemValue> getValue(String key);

    @Override
    default int getOrder(){
        return Ordered.LOWEST_PRECEDENCE;
    }
}