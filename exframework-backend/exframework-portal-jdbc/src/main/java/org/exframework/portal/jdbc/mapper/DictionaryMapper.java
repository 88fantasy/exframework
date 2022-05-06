package org.exframework.portal.jdbc.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.exframework.portal.jdbc.entity.base.DictionaryDO;
import org.exframework.support.jdbc.mapper.ExBaseMapper;

/**
 * 字典映射类
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@InterceptorIgnore(tenantLine = "off", dynamicTableName = "off")
public interface DictionaryMapper extends ExBaseMapper<DictionaryDO> {

}
