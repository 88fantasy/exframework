package org.exframework.portal.web.entity.hov;

import org.exframework.portal.metadata.hov.HovEntity;
import org.exframework.portal.web.dao.DataItemHovDao;
import org.exframework.portal.web.dto.DataItemHovRequest;

/**
 * 数据项参照
 * @author rwe
 * @since Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@HovEntity( value = "dataItemHov", name = "数据项参照", requestEntity = DataItemHovRequest.class, returnKey = "code", hovDao = DataItemHovDao.class, forceUpdate = true )
public class DataItemHov {

}
