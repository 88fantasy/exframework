package com.gzmpc.portal.admin.entity.hov;

import com.gzmpc.portal.admin.dao.HovDataClassHovDao;
import com.gzmpc.portal.admin.dto.HovDataClassHovRequest;
import com.gzmpc.portal.metadata.hov.HovEntity;

/**
 * 参照实现类Hov
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@HovEntity( value = "hovDataClassHov", name = "参照实现类参照", requestEntity = HovDataClassHovRequest.class, returnKey = "code", hovDao = HovDataClassHovDao.class, forceUpdate = true )
public class HovDataClassHov {

}
