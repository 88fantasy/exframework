package com.gzmpc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzmpc.core.entity.DictionaryDO;
import com.gzmpc.core.mapper.DictionaryMapper;
import com.gzmpc.core.util.MapperUtil;
import com.gzmpc.dao.DictionaryDao;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.FilterCondition;
import com.gzmpc.metadata.dict.DictionaryItem;
import com.gzmpc.support.common.entity.PageModel;

/**
 * 字典数据类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class DictionaryDaoImpl extends MetaDaoImpl<DictionaryDO, DictionaryItem> implements DictionaryDao, MapperUtil<DictionaryDO> {
	
	@Autowired
	DictionaryMapper dictionaryMapper;

	@Override
	public Map<String, String> findMapByKey(String code) throws NotFoundException {
		DictionaryDO entity = dictionaryMapper.selectById(code);
		if( entity == null) {
			throw new NotFoundException();
		}
		Map<String, String> dict = new ConcurrentHashMap<String,String>();
		String json = entity.getValueJson();
		JSONObject map = JSON.parseObject(json);
		for( String key : map.keySet()) {
			dict.put(key, map.getString(key));
		}
		return dict;
	}

	@Override
	public boolean saveDictionary(String code,  String name, Map<String, String> value) {
		DictionaryDO entity = dictionaryMapper.selectById(code);
		Map<String, Object> object = new ConcurrentHashMap<String, Object>();
		object.putAll(value);
		String json = new JSONObject(object).toJSONString();
		if(entity == null) {
			DictionaryDO newEntity = genInstance();
			newEntity.setCode(code);
			newEntity.setName(name);
			newEntity.setValueJson(json);
			dictionaryMapper.insert(newEntity);
		}
		else {
			entity.setValueJson(json);
			dictionaryMapper.updateById(entity);
		}
		return true;
	}

	@Override
	public BaseMapper<DictionaryDO> getBaseMapper() {
		return dictionaryMapper;
	}

	@Override
	public DictionaryDO genInstance() {
		return new DictionaryDO();
	}

	@Override
	public PageModel<DictionaryItem> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page) {
		Page<DictionaryDO> p = dictionaryMapper.selectPage(new Page<DictionaryDO>(page.getCurrent(), page.getPageSize()), wrapperFromCondition(params));
		return modelFromPage(p,DictionaryItem.class);
	}

	@Override
	public List<DictionaryItem> list(Collection<FilterCondition> params) {
		return new ArrayList<DictionaryItem>(dictionaryMapper.selectList(wrapperFromCondition(params)));
	}
}
