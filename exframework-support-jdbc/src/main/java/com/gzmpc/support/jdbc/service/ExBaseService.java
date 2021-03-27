package com.gzmpc.support.jdbc.service;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzmpc.support.jdbc.mapper.ExBaseMapper;

/**
 * @author rwe
 * @version 创建时间：2021年3月27日 上午11:03:35 基础 CRUD 服务
 */

public class ExBaseService<M extends ExBaseMapper<T>, T> extends ServiceImpl<M, T> {

	@Autowired
	protected M baseMapper;

	@Override
	public M getBaseMapper() {
		return baseMapper;
	}

	protected Class<T> entityClass = currentModelClass();

	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected Class<T> mapperClass = currentMapperClass();


	/**
	 * 存在更新记录，否插入一条记录, 并触发前置处理
	 * @param t	数据实体
	 * @param beforeSave	插入前
	 * @param beforeUpdate	更新前
	 * @return
	 */
	public boolean saveOrUpdateBefore(T t, Consumer<T> beforeSave, Consumer<T> beforeUpdate) {
		return saveOrUpdateTrigger(t,beforeSave, null, beforeUpdate, null);
	}
	
	/**
	 * 存在更新记录，否插入一条记录, 并触发后置处理
	 * @param t	数据实体
	 * @param afterSave 插入后
	 * @param afterUpdate	更新后
	 * @return
	 */
	public boolean saveOrUpdateAfter(T t, Consumer<T> afterSave, Consumer<T> afterUpdate) {
		return saveOrUpdateTrigger(t, null, afterSave, null, afterUpdate);
	}
	
	/**
	 * 存在更新记录，否插入一条记录, 并触发前置处理和后置统一处理
	 * @param t	数据实体
	 *  @param beforeSave	插入前
	 * @param beforeUpdate	更新前
	 * @param after 
	 * @return
	 */
	public boolean saveOrUpdateBeforeAndAfter(T t, Consumer<T> beforeSave, Consumer<T> beforeUpdate, Consumer<T> after) {
		return saveOrUpdateTrigger(t,beforeSave, after, beforeUpdate, after);
	}
	
	/**
	 * 存在更新记录，否插入一条记录, 并触发处理
	 * @param t 数据实体
	 * @param beforeSave 插入前
	 * @param afterSave	插入后
	 * @param beforeUpdate 更新前
	 * @param afterUpdate	更新后
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean saveOrUpdateTrigger(T t, Consumer<T> beforeSave, Consumer<T> afterSave, Consumer<T> beforeUpdate, Consumer<T> afterUpdate) {
		if (null != t) {
			TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
			Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
			String keyProperty = tableInfo.getKeyProperty();
			Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
			Object idVal = ReflectionKit.getFieldValue(t, tableInfo.getKeyProperty());
			boolean save = StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal));
			if(save) {
				if (beforeSave != null) {
					beforeSave.accept(t);
				}
				boolean success = save(t);
				if( success && afterSave != null) {
					afterSave.accept(t);
				}
			}
			else {
				if (beforeUpdate != null) {
					beforeUpdate.accept(t);
				}
				boolean success = updateById(t);
				if( success && afterUpdate != null) {
					afterUpdate.accept(t);
				}
			}
		}
		return false;
	}
}
