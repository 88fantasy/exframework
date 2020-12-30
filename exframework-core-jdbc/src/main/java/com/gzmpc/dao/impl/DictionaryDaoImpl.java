package com.gzmpc.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.DictionaryDO;
import com.gzmpc.core.entity.mapper.DictionaryMapper;
import com.gzmpc.dao.DictionaryDao;
import com.gzmpc.exception.NotFoundException;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class DictionaryDaoImpl implements DictionaryDao {
	
	@Autowired
	DictionaryMapper dictionaryMapper;

	@Override
	public Map<String, String> findByKey(String dictKey) throws NotFoundException {
		List<DictionaryDO> entities = dictionaryMapper.selectList(new QueryWrapper<DictionaryDO>().eq("dictKey", dictKey));
		if( entities == null || entities.size() == 0) {
			throw new NotFoundException();
		}
		Map<String, String> dict = new ConcurrentHashMap<String,String>();
		for( DictionaryDO entity : entities) {
			dict.put(entity.getItemKey(), entity.getItemValue());
		}
		return dict;
	}

	@Override
	public String[] allKeys() {
		List<DictionaryDO> entities = dictionaryMapper.selectList(new QueryWrapper<DictionaryDO>().select(" distinct dictKey "));
		return entities.stream().map(DictionaryDO::getDictKey).collect(Collectors.toList()).toArray(new String[entities.size()]);
	}

}
