package com.gzmpc.service.sys;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.ModuleDao;
import com.gzmpc.metadata.module.Module;
import com.gzmpc.metadata.module.ModuleEntity;

/**
 * 模块业务类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Service
public class ModuleService {

	@Autowired
	ModuleDao moduleDao;
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	DataItemService dataItemService;
	
	@Autowired
	HovService hovService;
	
	public Collection<String> getAllKeys() {
		return moduleDao.allKeys();
	}
	
	@Nullable
	public Module findByKey(String key) {
		ModuleEntity entity = moduleDao.findByKey(key);
		Module module = new Module();
		BeanUtils.copyProperties(entity, module);
		Collection<String> dataItemKeys = getDataItems(entity);
		Collection<String> permissions = moduleDao.findPermissionKeyByEntity(entity);
		Collection<String> hovs = moduleDao.findHovKeyByEntity(entity);
		module.setDataItems(dataItemKeys.stream().map(itemKey -> dataItemService.findDataItem(entity.getKey(), itemKey)).collect(Collectors.toList()));
		module.setPermissions(permissions);
		module.setHovs(hovs.stream().map(hovKey -> hovService.findByKey(key)).collect(Collectors.toList()));
		return module;
	}
	
	private Collection<String>  getDataItems(ModuleEntity entity) {
		return Collections.emptyList();
	}
}
