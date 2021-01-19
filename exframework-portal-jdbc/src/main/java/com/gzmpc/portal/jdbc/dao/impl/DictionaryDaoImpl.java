package com.gzmpc.portal.jdbc.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.portal.dao.DictionaryDao;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.dict.DictionaryItem;
import com.gzmpc.portal.jdbc.entity.DictionaryDO;
import com.gzmpc.portal.jdbc.mapper.DictionaryMapper;
import com.gzmpc.support.common.entity.PageModel;

/**
 * 字典数据类 Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class DictionaryDaoImpl extends MetaDaoImpl<DictionaryDO, DictionaryItem>
		implements DictionaryDao {
	@Autowired
	DictionaryMapper dictionaryMapper;

	@Override
	public Map<String, String> findMapByKey(String code) throws NotFoundException {
		DictionaryDO entity = dictionaryMapper.selectById(code);
		if (entity == null) {
			throw new NotFoundException();
		}
		return entity.getValue();
	}

	@Override
	public boolean saveDictionary(String code, String name, Map<String, String> value) {
		DictionaryDO entity = dictionaryMapper.selectById(code);
		if (entity == null) {
			DictionaryDO newEntity = genInstance();
			newEntity.setCode(code);
			newEntity.setName(name);
			newEntity.setValue(value);
			dictionaryMapper.insert(newEntity);
		} else {
			entity.setValue(value);
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
	public PageModel<DictionaryItem> query(Collection<FilterCondition> params,
			com.gzmpc.support.common.entity.Page page) {
		return dictionaryMapper.query(params, page, DictionaryItem.class);
	}

	@Override
	public List<DictionaryItem> list(Collection<FilterCondition> params) {
		return dictionaryMapper.list(params, DictionaryItem.class);
	}
}
