package com.gzmpc.portal.service.sys;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.dao.ModuleDao;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.portal.metadata.module.IModuleService;
import com.gzmpc.portal.metadata.module.Module;
import com.gzmpc.portal.metadata.module.ModuleEntity;
import com.gzmpc.portal.metadata.sys.Permission;
import com.gzmpc.support.common.util.SpringContextUtils;

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
	
//	@Nullable
//	public Module findByKey(String key) {
//		ModuleBase entity = moduleDao.findByKey(key);
//		Module module = new Module();
//		BeanUtils.copyProperties(entity, module);
//		Collection<String> dataItemKeys = getDataItems(entity);
//		Collection<String> permissions = moduleDao.findPermissionKeyByEntity(entity);
//		Collection<String> hovs = moduleDao.findHovKeyByEntity(entity);
//		module.setDataItems(dataItemKeys.stream().map(itemKey -> dataItemService.findDataItem(entity.getCode(), itemKey)).collect(Collectors.toList()));
//		module.setPermissions(permissions);
//		module.setHovs(hovs.stream().map(hovKey -> hovService.findByKey(key)).collect(Collectors.toList()));
//		return module;
//	}
//	
//	private Collection<String>  getDataItems(ModuleBase entity) {
//		return Collections.emptyList();
//	}
	
	public Module findByKey(String key) {
		Module module = new Module();
		ApplicationContext ac = SpringContextUtils.getApplicationContext();
		Map<String, Object> beans = ac.getBeansWithAnnotation(ModuleEntity.class);
		for (Map.Entry<String,Object> entry : beans.entrySet()) {
			Object bean = entry.getValue();
			ModuleEntity entity = bean.getClass().getAnnotation(ModuleEntity.class);
			if(entity != null && entity.value().equals(key)) {
				module.setCode(entity.value());
				module.setName(entity.name());
				module.setDescription(entity.description());
				
				if(IModuleService.class.isAssignableFrom(bean.getClass())) {
					IModuleService moduleService = (IModuleService) bean;
					Collection<DataItem> serviceItems = moduleService.getDataItems();
					Collection<Hov> serviceHovs = moduleService.getHovs();
					Collection<Permission> servicePermissions = moduleService.getPermissions();
					
					if(serviceItems.isEmpty()) {
						module.setDataItems(Arrays.asList(entity.dataItemRef()).stream().map(itemKey -> dataItemService.findDataItem(entity.value(), itemKey)).collect(Collectors.toList()));
					}
					else {
						module.setDataItems(serviceItems);
					}
					if(serviceHovs.isEmpty()) {
						module.setHovs(Arrays.asList(entity.hovRef()).stream().map(itemKey -> hovService.findByKey(itemKey)).collect(Collectors.toList()));
					}
					else {
						module.setHovs(serviceHovs);;
					}
				}
				
				break;
			}
		}
		return module;
	}
		
		
}
