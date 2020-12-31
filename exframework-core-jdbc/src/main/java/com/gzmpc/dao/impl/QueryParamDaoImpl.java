package com.gzmpc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.QueryParamDO;
import com.gzmpc.core.mapper.QueryParamMapper;
import com.gzmpc.dao.QueryParamDao;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.queryparam.QueryParam;

/**
 *
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class QueryParamDaoImpl implements QueryParamDao {
	
	@Autowired
	QueryParamMapper queryParamMapper;

	@Override
	public Collection<String> allKeys() {
		return all().stream().map(QueryParam::getCode).collect(Collectors.toList());
	}

	@Override
	public Collection<QueryParam> all() {
		return new ArrayList<QueryParam>(queryParamMapper.selectList(null));
	}

	@Override
	public QueryParam findByKey(String key) throws NotFoundException {
		return queryParamMapper.selectOne(new QueryWrapper<QueryParamDO>().eq("key", key));
	}

}
