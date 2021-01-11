package com.gzmpc.portal.jdbc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.portal.dao.QueryParamDao;
import com.gzmpc.portal.metadata.queryparam.QueryParam;
import com.gzmpc.portal.jdbc.entity.QueryParamDO;
import com.gzmpc.portal.jdbc.mapper.QueryParamMapper;

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
