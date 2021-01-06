package com.gzmpc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.core.entity.DataItemDO;
import com.gzmpc.core.entity.DataItemExtendDO;
import com.gzmpc.core.mapper.DataItemExtendMapper;
import com.gzmpc.core.mapper.DataItemMapper;
import com.gzmpc.dao.DataItemDao;
import com.gzmpc.metadata.di.DataItem;

/**
 * 数据项数据类 Author: rwe Date: Dec 31, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class DataItemDaoImpl extends MetaDaoImpl<DataItemDO, DataItem> implements DataItemDao {

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
	public DataItem findExtend(String objectCode, String key) {
		return dataItemExtendMapper.selectOne(new QueryWrapper<DataItemExtendDO>().eq("key", key).eq("objectCode", objectCode));
	}

	@Override
	public DataItemDO genInstance() {
		return new DataItemDO();
	}

}
