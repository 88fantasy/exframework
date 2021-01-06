package com.gzmpc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.lang.Nullable;

import com.gzmpc.metadata.di.DataItem;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface DataItemDao extends MetaDao<DataItem> {

	Map<String,List<DataItem>> allExtends();
	
	@Nullable
	DataItem findExtend(String objectCode, String code);
}
