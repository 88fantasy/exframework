package org.exframework.portal.metadata.module;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.hov.Hov;
import org.exframework.portal.metadata.sys.Permission;
import org.exframework.portal.service.sys.HovService;
import org.exframework.support.common.util.SpringContextUtils;

/**
 *
 * @author rwe
 * @since Jan 14, 2021
 *
 * Copyright @ 2021 
 * 
 */
public interface IModuleService {
	
	static Logger log = LoggerFactory.getLogger(IModuleService.class.getName());
	
	default HovService getHovService() {
		return SpringContextUtils.getBeanByClass(HovService.class);
	}

	default Collection<DataItem> getDataItems() {
		 return Collections.emptyList();
	}
	
	default Collection<Permission> getPermissions() {
		return Collections.emptyList();
	}
	
	default Collection<Hov> getHovs() {
		return Collections.emptyList();
	}
}
