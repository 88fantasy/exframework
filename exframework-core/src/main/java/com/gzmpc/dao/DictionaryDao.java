package com.gzmpc.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.FilterCondition;
import com.gzmpc.metadata.dict.DictionaryItem;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface DictionaryDao extends MetaDao<DictionaryItem> {

	Map<String,String> findMapByKey(String dictKey) throws NotFoundException;
	
	boolean saveDictionary(String code, String name, Map<String,String> value);
	
	PageModel<DictionaryItem> query(Collection<FilterCondition> params, Page page);
	
	List<DictionaryItem> list(Collection<FilterCondition> params);
}
