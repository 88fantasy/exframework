package com.gzmpc.portal.jdbc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzmpc.portal.dao.DataItemDao;
import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.di.DataItemExtend;
import com.gzmpc.portal.jdbc.entity.DataItemDO;
import com.gzmpc.portal.jdbc.entity.DataItemExtendDO;
import com.gzmpc.portal.jdbc.mapper.DataItemExtendMapper;
import com.gzmpc.portal.jdbc.mapper.DataItemMapper;
import com.gzmpc.portal.jdbc.util.MapperUtil;
import com.gzmpc.support.common.entity.PageModel;

/**
 * 数据项数据类 Author: rwe Date: Dec 31, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class DataItemDaoImpl extends MetaDaoImpl<DataItemDO, DataItem> implements DataItemDao, MapperUtil<DataItemDO> {

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
		Map<String, List<DataItem>> result = new ConcurrentHashMap<String, List<DataItem>>();
		for (String objectCode : extendMap.keySet()) {
			List<DataItemExtendDO> extend = extendMap.get(objectCode);
			List<DataItem> list = new ArrayList<DataItem>(extend);
			result.put(objectCode, list);
		}
		return result;
	}

	@Override
	public Collection<DataItem> findExtendByObjectCode(String objectCode) {
		List<DataItemExtendDO> extendList = dataItemExtendMapper.selectList(new LambdaQueryWrapper<DataItemExtendDO>().eq(DataItemExtendDO::getObjectCode, objectCode));
		return new ArrayList<DataItem>(extendList);
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
	public PageModel<DataItem> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page) {
		Page<DataItemDO> p = dataItemMapper.selectPage(new Page<DataItemDO>(page.getCurrent(), page.getPageSize()), wrapperFromCondition(params));
		return modelFromPage(p,DataItem.class);
	}

	@Override
	public List<DataItem> list(Collection<FilterCondition> params) {
		return new ArrayList<DataItem>(dataItemMapper.selectList(wrapperFromCondition(params)));
	}


}
