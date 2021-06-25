package org.exframework.portal.jdbc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exframework.portal.dao.QueryParamDao;
import org.exframework.portal.metadata.queryparam.QueryParam;
import org.exframework.portal.jdbc.entity.QueryParamDO;
import org.exframework.portal.jdbc.mapper.QueryParamMapper;

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
