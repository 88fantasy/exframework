package org.exframework.portal.admin.entity.hov;

import org.exframework.portal.admin.dao.HovDataClassHovDao;
import org.exframework.portal.admin.dto.HovDataClassHovRequest;
import org.exframework.portal.metadata.hov.HovEntity;

/**
 * 参照实现类Hov
 * @author rwe
 * @since Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@HovEntity( value = "hovDataClassHov", name = "参照实现类参照", requestEntity = HovDataClassHovRequest.class, returnKey = "code", hovDao = HovDataClassHovDao.class, forceUpdate = true )
public class HovDataClassHov {

}
