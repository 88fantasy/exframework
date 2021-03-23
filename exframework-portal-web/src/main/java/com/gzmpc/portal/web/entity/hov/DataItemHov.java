package com.gzmpc.portal.web.entity.hov;

import com.gzmpc.portal.metadata.hov.HovEntity;
import com.gzmpc.portal.web.dao.DataItemHovDao;
import com.gzmpc.portal.web.dto.DataItemHovRequest;

/**
 * 数据项参照
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@HovEntity( value = "dataItemHov", name = "数据项参照", requestEntity = DataItemHovRequest.class, returnKey = "code", hovDao = DataItemHovDao.class, forceUpdate = true )
public class DataItemHov {

}
