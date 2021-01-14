package com.gzmpc.portal.metadata.module;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.portal.metadata.sys.Permission;
import com.gzmpc.portal.service.sys.HovService;
import com.gzmpc.support.common.util.SpringContextUtils;

/**
 *
 * Author: rwe
 * Date: Jan 14, 2021
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
