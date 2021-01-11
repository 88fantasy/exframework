package com.gzmpc.portal.dao;

import java.util.Collection;

import com.gzmpc.portal.metadata.module.Module;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface ModuleDao extends MetaDao<Module> {
	
	Collection<String> findPermissionKeyByEntity(Module entity);
	
	Collection<String> findHovKeyByEntity(Module entity);
}
