package com.gzmpc.dao;

import java.util.Collection;

import com.gzmpc.metadata.module.ModuleEntity;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface ModuleDao extends MetaDao<ModuleEntity> {
	
	Collection<String> findPermissionKeyByEntity(ModuleEntity entity);
	
	Collection<String> findHovKeyByEntity(ModuleEntity entity);
}
