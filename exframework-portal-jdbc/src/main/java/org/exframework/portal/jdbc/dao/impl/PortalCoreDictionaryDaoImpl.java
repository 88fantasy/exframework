package org.exframework.portal.jdbc.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.exframework.portal.dao.PortalCoreDictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.portal.metadata.dict.DictionaryItem;
import org.exframework.portal.jdbc.entity.DictionaryDO;
import org.exframework.portal.jdbc.mapper.DictionaryMapper;
import org.exframework.support.common.entity.PageModel;

/**
 * 字典数据类 Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Repository
public class PortalCoreDictionaryDaoImpl extends PortalCoreMetaDaoImpl<DictionaryDO, DictionaryItem>
		implements PortalCoreDictionaryDao {
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
		return saveDictionary(code, name, value, false);
	}

	@Override
	public boolean saveDictionary(String code, String name, Map<String, String> value, boolean local) {
		DictionaryDO entity = dictionaryMapper.selectById(code);
		if (entity == null) {
			DictionaryDO newEntity = genInstance();
			newEntity.setCode(code);
			newEntity.setName(name);
			newEntity.setValue(value);
			newEntity.setLocal(local);
			dictionaryMapper.insert(newEntity);
		} else {
			entity.setName(name);
			entity.setValue(value);
			entity.setLocal(local);
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
	public DictionaryItem transform(DictionaryDO dictionaryDO) {
		return dictionaryDO;
	}

	@Override
	public PageModel<DictionaryItem> query(Collection<FilterCondition> params,
			org.exframework.support.common.entity.Page page) {
		return dictionaryMapper.query(params, page, DictionaryItem.class);
	}

	@Override
	public List<DictionaryItem> list(Collection<FilterCondition> params) {
		return dictionaryMapper.list(params, DictionaryItem.class);
	}

}
