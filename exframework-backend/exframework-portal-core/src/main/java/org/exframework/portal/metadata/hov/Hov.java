package org.exframework.portal.metadata.hov;

import org.exframework.portal.metadata.Meta;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.di.DataItemRef;
import org.exframework.portal.metadata.grid.Column;
import org.exframework.portal.pub.PageRequest;
import org.exframework.portal.service.sys.PortalCoreDataItemService;
import org.exframework.support.common.util.SpringContextUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Hov extends Meta {

	private static final long serialVersionUID = 8096784702772680510L;
	
	/**
	 * 请求字段
	 */
	private HovQueryParams[] queryParams;
	
	/**
	 * 返回字段
	 */
	private Column[] columns;
	
	/**
	 * 数据类名
	 */
	private String dataClass;
	
	/**
	 * 返回字段
	 */
	private String returnKey;

	
	public Hov() {
		super();
	}

	public HovQueryParams[] getQueryParams() {
		return queryParams;
	}





	public void setQueryParams(HovQueryParams[] queryParams) {
		this.queryParams = queryParams;
	}





	public Column[] getColumns() {
		return columns;
	}





	public void setColumns(Column[] columns) {
		this.columns = columns;
	}





	public String getDataClass() {
		return dataClass;
	}

	public void setDataClass(String dataClass) {
		this.dataClass = dataClass;
	}

	public String getReturnKey() {
		return returnKey;
	}

	public void setReturnKey(String returnKey) {
		this.returnKey = returnKey;
	}
	

	public static Hov instanceByClass(PortalCoreDataItemService portalCoreDataItemService, String code, String name, String description, Class<? extends PageRequest> requestClass, Class<? extends IHovDao<?>> dataClass, String returnKey) {
		Hov hov = new Hov();
		hov.setCode(code);
		hov.setName(name);
		hov.setDescription(description);
		hov.setDataClass(dataClass.getName());
		hov.setReturnKey(returnKey);
		final List<HovQueryParams> params = new ArrayList<HovQueryParams>();
		ReflectionUtils.doWithFields(requestClass, new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				HovQueryParams param = new HovQueryParams();
				param.setKey(field.getName());
				param.setDataIndex(field.getName());
				if (field.isAnnotationPresent(DataItemRef.class)) {
					DataItemRef ref = field.getAnnotation(DataItemRef.class);
					String dataItemCode = ref.value();
					String objectCode = ref.objectCode();
					DataItem item = portalCoreDataItemService.findDataItem(objectCode, dataItemCode);
					if(item != null) {
						param.setDataIndex(item.getCode());
					}
				}
				if(!params.stream().anyMatch(p -> p.getKey().contentEquals(param.getKey()) && p.getDataIndex().contentEquals(param.getDataIndex()) )) {
					params.add(param);
				}
			}
		}, new ReflectionUtils.FieldFilter() {
			@Override
			public boolean matches(Field field) {
				return !Modifier.isStatic(field.getModifiers());
			}
		});
		hov.setQueryParams(params.stream().toArray(HovQueryParams[]::new));
		
		final List<Column> columns = new ArrayList<Column>();
		IHovDao<?> dao = SpringContextUtils.getBeanByClass(dataClass);
		ReflectionUtils.doWithFields(dao.getEntityClass(), new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				Column column = new Column();
				String fieldName = field.getName();
				column.setKey(fieldName);
				column.setDataIndex(fieldName);
				if (field.isAnnotationPresent(DataItemRef.class)) {
					DataItemRef ref = field.getAnnotation(DataItemRef.class);
					String dataItemCode = ref.value();
					String objectCode = ref.objectCode();
					DataItem item = portalCoreDataItemService.findDataItem(objectCode, dataItemCode);
					if(item != null) {
						column.setDataIndex(item.getCode());
					}
				}
				else {
					DataItem item = portalCoreDataItemService.findDataItem(code, fieldName);
					if(item != null) {
						column.setKey(item.getCode());
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
		hov.setColumns(columns.stream().toArray(Column[]::new));
		return hov;
	}
}
