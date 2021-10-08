package org.exframework.portal.jdbc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.exframework.portal.dao.PortalCoreDataItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.di.DataItemExtend;
import org.exframework.portal.jdbc.entity.base.DataItemDO;
import org.exframework.portal.jdbc.entity.base.DataItemExtendDO;
import org.exframework.portal.jdbc.mapper.DataItemExtendMapper;
import org.exframework.portal.jdbc.mapper.DataItemMapper;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

/**
 * 数据项数据类 Author: rwe Date: Dec 31, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class PortalCoreDataItemDaoImpl extends PortalCoreMetaDaoImpl<DataItemDO, DataItem> implements PortalCoreDataItemDao {

	@Autowired
	DataItemMapper dataItemMapper;

	@Autowired
	DataItemExtendMapper dataItemExtendMapper;

	@Override
	public BaseMapper<DataItemDO> getBaseMapper() {
		return dataItemMapper;
	}

	@Override
	public Map<String, List<DataItem>> allExtends() {
		List<DataItemExtendDO> extendList = dataItemExtendMapper.selectList(null);
		ConcurrentMap<String, List<DataItemExtendDO>> extendMap = extendList.parallelStream()
				.collect(Collectors.groupingByConcurrent(DataItemExtendDO::getObjectCode));
		Map<String, List<DataItem>> result = new ConcurrentHashMap<>();
		for (String objectCode : extendMap.keySet()) {
			List<DataItemExtendDO> extend = extendMap.get(objectCode);
			List<DataItem> list = new ArrayList<>(extend);
			result.put(objectCode, list);
		}
		return result;
	}

	@Override
	public Collection<DataItem> findExtendByObjectCode(String objectCode) {
		List<DataItemExtendDO> extendList = dataItemExtendMapper.selectList(new LambdaQueryWrapper<DataItemExtendDO>().eq(DataItemExtendDO::getObjectCode, objectCode));
		return new ArrayList<>(extendList);
	}

	@Override
	public DataItem findExtend(String objectCode, String code) {
		DataItemExtendDO entity = dataItemExtendMapper.selectOne(new LambdaQueryWrapper<DataItemExtendDO>().eq(DataItemExtendDO::getCode, code).eq(DataItemExtendDO::getObjectCode, objectCode));
		if(entity != null) {
			entity.setCode(entity.getCode()+DataItemExtend.SPLITER+objectCode);
		}
		return entity;
	}

	@Override
	public DataItemDO genInstance() {
		return new DataItemDO();
	}

	@Override
	public DataItem transform(DataItemDO dataItemDO) {
		return dataItemDO;
	}

	@Override
	public PageModel<DataItem> query(Collection<FilterCondition> params, org.exframework.support.common.entity.Page page) {
		return dataItemMapper.query(params, page, DataItem.class);
	}

	@Override
	public PageModel<DataItem> query(Collection<FilterCondition> params, Page page, Collection<String> sorts) {
		return dataItemMapper.query(params, page, sorts, DataItem.class);
	}

	@Override
	public List<DataItem> list(Collection<FilterCondition> params) {
		return dataItemMapper.list(params, DataItem.class);
	}


}
