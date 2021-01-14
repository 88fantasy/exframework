package com.gzmpc.portal.web.entity.hov;

import com.gzmpc.portal.metadata.hov.HovEntity;
import com.gzmpc.portal.web.dao.ModuleHovDao;
import com.gzmpc.portal.web.dto.ModuleHovRequest;

/**
 * 数据项参照
 * Author: rwe
 * Date: Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@HovEntity( value = "moduleHov", name = "功能模块参照", requestEntity = ModuleHovRequest.class, returnKey = "code", hovDao = ModuleHovDao.class )
public class ModuleHov {

}
