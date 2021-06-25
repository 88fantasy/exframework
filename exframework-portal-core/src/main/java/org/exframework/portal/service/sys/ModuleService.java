package org.exframework.portal.service.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import org.exframework.portal.dao.ModuleDao;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.grid.Column;
import org.exframework.portal.metadata.hov.Hov;
import org.exframework.portal.metadata.hov.HovQueryParams;
import org.exframework.portal.metadata.module.IModuleService;
import org.exframework.portal.metadata.module.Module;
import org.exframework.portal.metadata.module.ModuleBase;
import org.exframework.portal.metadata.module.ModuleEntity;
import org.exframework.portal.metadata.sys.Permission;
import org.exframework.support.common.util.SpringContextUtils;

/**
 * 模块业务类 Author: rwe Date: Dec 29, 2020
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

	public Module instanceByClass(ModuleBase entity, String[] dataItemCodes, String[] hovCodes, String[] permissions) {
		List<DataItem> dataItems = Arrays.asList(dataItemCodes).stream().map(itemKey -> dataItemService.findDataItem(entity.getCode(), itemKey)).collect(Collectors.toList());
		List<Hov> hovs = Arrays.asList(hovCodes).stream().map(code -> hovService.findByKey(code))
				.collect(Collectors.toList());
		Module module = new Module(entity.getCode(), entity.getName(), entity.getDescription(), entity.isValid(),
				entity.getStar(), dataItems, Arrays.asList(permissions), hovs);
		return module;
	}

	public Module findByKey(String key) {
		Module module = new Module();
		ApplicationContext ac = SpringContextUtils.getApplicationContext();
		Map<String, Object> beans = ac.getBeansWithAnnotation(ModuleEntity.class);
		for (Map.Entry<String, Object> entry : beans.entrySet()) {
			Object bean = entry.getValue();
			ModuleEntity entity = bean.getClass().getAnnotation(ModuleEntity.class);
			if (entity != null && entity.value().equals(key)) {
				module.setCode(entity.value());
				module.setName(entity.name());
				module.setDescription(entity.description());

				if (IModuleService.class.isAssignableFrom(bean.getClass())) {
					IModuleService moduleService = (IModuleService) bean;
					Collection<DataItem> serviceItems = moduleService.getDataItems();
					Collection<Hov> serviceHovs = moduleService.getHovs();
					Collection<Permission> servicePermissions = moduleService.getPermissions();
					List<Hov> moduleHovs = new ArrayList<Hov>();
					List<DataItem> moduleItems = new ArrayList<DataItem>();

					if (serviceHovs.isEmpty()) {
						moduleHovs.addAll(Arrays.asList(entity.hovRef()).stream()
								.map(itemKey -> hovService.findByKey(itemKey)).collect(Collectors.toList()));
					} else {
						moduleHovs.addAll(serviceHovs);
					}
					if (serviceItems.isEmpty()) {
						moduleItems.addAll(Arrays.asList(entity.dataItemRef()).stream()
								.map(itemKey -> dataItemService.findDataItem(entity.value(), itemKey))
								.collect(Collectors.toList()));
					} else {
						moduleItems.addAll(serviceItems);
					}
					for (Hov hov : moduleHovs) {
						HovQueryParams[] params = hov.getQueryParams();
						if (params != null) {
							for (HovQueryParams p : params) {
								DataItem item = dataItemService.findDataItemWithSpliter(p.getKey());
								if (item != null) {
									if (!contains(moduleItems, item)) {
										moduleItems.add(item);
									}
								}
							}
						}
						Column[] columns = hov.getColumns();
						if (columns != null) {
							for (Column c : columns) {
								DataItem item = dataItemService.findDataItemWithSpliter(c.getKey());
								if (item != null) {
									if (!contains(moduleItems, item)) {
										moduleItems.add(item);
									}
								}
							}
						}
					}
					module.setHovs(moduleHovs);
					module.setDataItems(moduleItems);
				}

				break;
			}
		}
		return module;
	}

	private boolean contains(Collection<DataItem> items, DataItem item) {
		if (items == null) {
			return false;
		} else {
			return items.stream().anyMatch(r -> r.getCode().contentEquals(item.getCode()));
		}
	}
}
