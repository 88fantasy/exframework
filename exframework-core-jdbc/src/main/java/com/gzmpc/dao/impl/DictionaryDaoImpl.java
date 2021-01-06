package com.gzmpc.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.DictionaryDO;
import com.gzmpc.core.mapper.DictionaryMapper;
import com.gzmpc.dao.DictionaryDao;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.support.jdbc.exception.SessionExcetpion;

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
		List<DictionaryDO> entities = findListByKey(dictKey);
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveDictionary(String dictKey, Map<String, String> value) {
		List<DictionaryDO> entities = findListByKey(dictKey);
		if( entities != null && entities.size() > 0) {
			dictionaryMapper.delete(getWrapperByKey(dictKey));
		}
		List<DictionaryDO> newEntities = value.keySet().stream().map(key -> new DictionaryDO(dictKey, key, value.get(key))).collect(Collectors.toList());
		for(DictionaryDO entity : newEntities) {
			int success = dictionaryMapper.insert(entity);
			if(success < 1) {
				throw new SessionExcetpion("插入数据失败");
			}
		}
		return true;
	}

	private List<DictionaryDO> findListByKey(String dictKey) {
		return dictionaryMapper.selectList(getWrapperByKey(dictKey));
	}
	
	private QueryWrapper<DictionaryDO> getWrapperByKey(String dictKey) {
		return new QueryWrapper<DictionaryDO>().eq("dictKey", dictKey);
	}
}
