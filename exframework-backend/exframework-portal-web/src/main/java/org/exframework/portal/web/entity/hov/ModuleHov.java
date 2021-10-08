package org.exframework.portal.web.entity.hov;

import org.exframework.portal.metadata.hov.HovEntity;
import org.exframework.portal.web.dao.ModuleHovDao;
import org.exframework.portal.web.dto.ModuleHovRequest;

/**
 * 数据项参照
 * @author rwe
 * @since Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@HovEntity( value = "moduleHov", name = "功能模块参照", requestEntity = ModuleHovRequest.class, returnKey = "code", hovDao = ModuleHovDao.class, forceUpdate = true )
public class ModuleHov {

}
