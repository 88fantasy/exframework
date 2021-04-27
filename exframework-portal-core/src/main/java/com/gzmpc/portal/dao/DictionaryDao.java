package com.gzmpc.portal.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.dict.DictionaryItem;
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
	
	boolean saveDictionary(String code, String name, Map<String,String> value, boolean local);
	
	PageModel<DictionaryItem> query(Collection<FilterCondition> params, Page page);
	
	List<DictionaryItem> list(Collection<FilterCondition> params);
}
