package org.exframework.portal.jdbc.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.exframework.portal.jdbc.entity.base.DataItemDO;
import org.exframework.support.jdbc.mapper.ExBaseMapper;

/**
 * 数据项映射
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@InterceptorIgnore(tenantLine = "off", dynamicTableName = "off")
public interface DataItemMapper extends ExBaseMapper<DataItemDO> {

}
