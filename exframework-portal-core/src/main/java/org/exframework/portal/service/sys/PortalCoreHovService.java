package org.exframework.portal.service.sys;


import org.exframework.portal.dao.PortalCoreHovDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.exframework.portal.metadata.hov.Hov;

/**
 * 参照业务类
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Service
public class PortalCoreHovService {

	@Autowired
	PortalCoreHovDao portalCoreHovDao;
	
	@Autowired
	PortalCoreDataItemService portalCoreDataItemService;
	
	public Hov findByKey(String code) {
		return portalCoreHovDao.findByKey(code);
	}
}
