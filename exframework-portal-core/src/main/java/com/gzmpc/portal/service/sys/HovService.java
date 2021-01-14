package com.gzmpc.portal.service.sys;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.gzmpc.portal.dao.HovDao;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.di.DataItemRef;
import com.gzmpc.portal.metadata.grid.Column;
import com.gzmpc.portal.metadata.hov.HovBase;
import com.gzmpc.portal.metadata.hov.HovQueryParams;
import com.gzmpc.portal.metadata.hov.IHovDao;
import com.gzmpc.portal.pub.PageRequest;
import com.gzmpc.support.common.util.SpringContextUtils;

/**
 * 参照业务类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Service
public class HovService {

	@Autowired
	HovDao hovDao;
	
	@Autowired
	DataItemService dataItemService;
	
	public HovBase findByKey(String code) {
		return hovDao.findByKey(code);
	}
	
	@SuppressWarnings("unchecked")
	public Class<? extends PageRequest> getRequestClass(HovBase base) throws ClassNotFoundException, ClassCastException {
		return  (Class<? extends PageRequest>) Class.forName(base.getRequestClass());
	}
	
	public Collection<HovQueryParams> getHovQueryParams(HovBase base) throws ClassNotFoundException, ClassCastException {
		Class<?> clazz = Class.forName(base.getRequestClass());
		final List<HovQueryParams> params = new ArrayList<HovQueryParams>();
		ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				HovQueryParams param = new HovQueryParams();
				param.setCode(field.getName());
				param.setDataIndex(field.getName());
				param.setTitle(field.getName());
				if (field.isAnnotationPresent(DataItemRef.class)) {
					DataItemRef ref = field.getAnnotation(DataItemRef.class);
					String dataItemCode = ref.value();
					String objectCode = ref.objectCode();
					DataItem item = dataItemService.findDataItem(objectCode, dataItemCode);
					param.setTitle(item.getName());
					param.setCode(item.getCode());
				}
				params.add(param);
			}
		}, new ReflectionUtils.FieldFilter() {
			@Override
			public boolean matches(Field field) {
				return !Modifier.isStatic(field.getModifiers());
			}
		});
		return params;
	}
	
	@SuppressWarnings("unchecked")
	public Class<? extends IHovDao<?>> getDataClass(HovBase base) throws ClassNotFoundException, ClassCastException {
		return  (Class<? extends IHovDao<?>>) Class.forName(base.getDataClass());
	}
	
	public Collection<Column> getColumns(HovBase base) throws ClassNotFoundException, ClassCastException {
		Class<? extends IHovDao<?>> clazz = getDataClass(base);
		final List<Column> columns = new ArrayList<Column>();
		IHovDao<?> dao = SpringContextUtils.getBeanByClass(clazz);
		ReflectionUtils.doWithFields(dao.getEntityClass(), new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				Column column = new Column();
				String fieldName = field.getName();
				column.setCode(fieldName);
				column.setDataIndex(fieldName);
				column.setTitle(fieldName);
				if (field.isAnnotationPresent(DataItemRef.class)) {
					DataItemRef ref = field.getAnnotation(DataItemRef.class);
					String dataItemCode = ref.value();
					String objectCode = ref.objectCode();
					DataItem item = dataItemService.findDataItem(objectCode, dataItemCode);
					if(item != null) {
						column.setTitle(item.getName());
						column.setCode(item.getCode());
					}
				}
				else {
					DataItem item = dataItemService.findDataItem(base.getCode(), fieldName);
					if(item != null) {
						column.setTitle(item.getName());
						column.setCode(item.getCode());
					}
				}
				columns.add(column);
			}
		}, new ReflectionUtils.FieldFilter() {
			@Override
			public boolean matches(Field field) {
				return !Modifier.isStatic(field.getModifiers());
			}
		});
		return columns;
	}
}
