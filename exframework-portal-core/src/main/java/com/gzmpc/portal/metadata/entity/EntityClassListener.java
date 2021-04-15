package com.gzmpc.portal.metadata.entity;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.gzmpc.portal.dao.DataItemDao;
import com.gzmpc.portal.dao.HovDao;
import com.gzmpc.portal.dao.ModuleDao;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.di.DataItemEntity;
import com.gzmpc.portal.metadata.di.DataItem.DataItemDisplayTypeEnum;
import com.gzmpc.portal.metadata.di.DataItem.DataItemValueTypeEnum;
import com.gzmpc.portal.metadata.dict.Dictionary;
import com.gzmpc.portal.metadata.dict.DictionaryEnum;
import com.gzmpc.portal.metadata.dict.DictionaryEnumClass;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.portal.metadata.hov.HovEntity;
import com.gzmpc.portal.metadata.hov.IHovDao;
import com.gzmpc.portal.metadata.module.Module;
import com.gzmpc.portal.metadata.module.ModuleBase;
import com.gzmpc.portal.metadata.module.ModuleEntity;
import com.gzmpc.portal.pub.PageRequest;
import com.gzmpc.portal.service.sys.DataItemService;
import com.gzmpc.portal.service.sys.DdlService;
import com.gzmpc.portal.service.sys.ModuleService;

/**
 * 加载EntityClass注解
 * Author: rwe
 * Date: Jan 11, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Repository
public class EntityClassListener implements ApplicationListener<ApplicationReadyEvent> {
	
	private Logger log = LoggerFactory.getLogger(EntityClassListener.class.getName());

	@Autowired
	DdlService ddlService;
	
	@Autowired
	DataItemDao dataItemDao;

	@Autowired
	HovDao hovDao;
	
	@Autowired
	DataItemService dataItemService;
	
	@Autowired
	ModuleDao moduleDao;
	
	@Autowired
	ModuleService moduleService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ApplicationContext ac = event.getApplicationContext();
		log.info("开始扫描注解"+EntityScan.class.getName());
		Map<String, Object> entities = ac.getBeansWithAnnotation(EntityClass.class);
		for (String dn : entities.keySet()) {
			Object entityClass = entities.get(dn);
			Class<? extends Object> clazz = entityClass.getClass();
			log.info(MessageFormat.format("正在检查{0}", entityClass.getClass().getName()));
			ReflectionUtils.doWithFields(entityClass.getClass(), new ReflectionUtils.FieldCallback() {
				@Override
				public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
					ReflectionUtils.makeAccessible(field);
					Object value = field.get(entityClass);
					try {
						// 如果字段添加了我们自定义注解
						if (field.isAnnotationPresent(DataItemEntity.class)) {
							log.info(MessageFormat.format("正在检查数据项{0}",  field.getName()));
							DataItemEntity item = field.getAnnotation(DataItemEntity.class);
							String code = item.value();
							String name = item.name();
							String description = item.description();
							DataItemDisplayTypeEnum type = item.type();
							DataItemValueTypeEnum valueType = item.valueType();
							String displayKey = item.displayKey();
							int length = item.maxlength();
							int precision = item.precision();
							String objectCode = item.objectCode();
							boolean forceUpdate = item.forceUpdate();
							
							if(valueType == DataItemValueTypeEnum.DEFAULT) {
								valueType = dataItemDao.defaultValueType(value);
							}
							
							if(StringUtils.hasText(objectCode)) {
								
							}
							else {
								DataItem dataitem = dataItemDao.findByKey(code);
								if(dataitem == null) {
									dataitem = new DataItem(code, name, description, type, displayKey, valueType, length, precision);
									dataItemDao.insert(dataitem);
								}
								else if(forceUpdate) {
									dataitem = new DataItem(code, name, description, type, displayKey, valueType, length, precision);
									dataItemDao.update(dataitem);
								}
							}
						}
					} catch ( Exception e) {
						log.error(MessageFormat.format("设置配置项[{0}]失败: {1}", field.getName(), e.getMessage()),e);
					}
				}
			});
			
			if(DictionaryEnumClass.class.isAssignableFrom(clazz)) {
				DictionaryEnumClass dec = (DictionaryEnumClass) entityClass;
				Class<?>[] enumclasses =  dec.enums();
				if(enumclasses != null) {
					for(Class<?> enumclass : enumclasses) {
						Dictionary d = enumclass.getAnnotation(Dictionary.class);
						String dictName = d.name();
						String dictCode = d.value();
						if(!StringUtils.hasText(dictCode)) {
							String simpleName = enumclass.getSimpleName();
							dictCode = Character.toLowerCase(simpleName.charAt(0))+simpleName.substring(1);
						}
						if (enumclass.isEnum()) {
							log.info(MessageFormat.format("加载字典{0}", dictCode));
							Map<String, String> vv = new ConcurrentHashMap<String, String>();
							Object[] objs = enumclass.getEnumConstants();
							for (Object obj : objs) {
								if(DictionaryEnum.class.isAssignableFrom(obj.getClass())) {
									DictionaryEnum<?> e = (DictionaryEnum<?>) obj;
									String v = String.valueOf(e.getValue());
									String enumName = e.getName();
									vv.put(v, enumName);
								}
							}
							ddlService.saveDictionary(dictCode, dictName, vv);
						}
					}
				}
			}
			
			HovEntity hov = entityClass.getClass().getAnnotation(HovEntity.class);
			if(hov != null) {
				String code = hov.value();
				String name = hov.name();
				String description = hov.description();
				Class<? extends PageRequest> request = hov.requestEntity();
				Class<? extends IHovDao<?>> daoClass = hov.hovDao();
				String returnKey = hov.returnKey();
				boolean force = hov.forceUpdate();
				Hov entity = hovDao.findByKey(code);
				if(entity == null ) {
					entity = Hov.instanceByClass(dataItemService, code, name, description, request, daoClass, returnKey);
					hovDao.insert(entity);
				}
				else if(force) {
					entity = Hov.instanceByClass(dataItemService, code, name, description, request, daoClass, returnKey);
					hovDao.update(entity);
				}
			}
			
			ModuleEntity module = entityClass.getClass().getAnnotation(ModuleEntity.class);
			if(module != null) {
				String code = module.value();
				String name = module.name();
				String description = module.description();
				String[] items = module.dataItemRef();
				String[] hovs = module.hovRef();
				String[] permissions = module.permissionRef();
				boolean force = module.forceUpdate();
				ModuleBase base = moduleDao.findByKey(code);
				if(base == null ) {
					base = new ModuleBase(code, name, description);
					Module entity = moduleService.instanceByClass(base, items, hovs, permissions);
					moduleDao.insert(entity);
				}
				else if(force) {
					Module entity = moduleService.instanceByClass(base, items, hovs, permissions);
					moduleDao.update(entity);
				}
			}
			
		}
	}
}
