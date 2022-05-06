package org.exframework.portal.jdbc.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exframework.portal.jdbc.entity.base.DataItemExtendDO;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@InterceptorIgnore(tenantLine = "off", dynamicTableName = "off")
public interface DataItemExtendMapper extends BaseMapper<DataItemExtendDO> {

}
