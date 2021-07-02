package org.exframework.portal.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.exframework.portal.exception.NotFoundException;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.portal.metadata.dict.DictionaryItem;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
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
