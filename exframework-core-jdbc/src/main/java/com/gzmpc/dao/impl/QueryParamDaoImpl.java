package com.gzmpc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.core.entity.QueryParamDO;
import com.gzmpc.core.mapper.QueryParamMapper;
import com.gzmpc.dao.QueryParamDao;
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
public class QueryParamDaoImpl extends MetaDaoImpl<QueryParamDO,QueryParam> implements QueryParamDao {
	
	@Autowired
	QueryParamMapper queryParamMapper;

	@Override
	public BaseMapper<QueryParamDO> getBaseMapper() {
		return queryParamMapper;
	}

	@Override
	public QueryParamDO genInstance() {
		return new QueryParamDO();
	}


}
