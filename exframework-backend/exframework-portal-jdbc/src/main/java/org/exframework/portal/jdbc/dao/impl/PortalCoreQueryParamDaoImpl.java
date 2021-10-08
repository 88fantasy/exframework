package org.exframework.portal.jdbc.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exframework.portal.dao.PortalCoreQueryParamDao;
import org.exframework.portal.jdbc.entity.module.QueryParamDO;
import org.exframework.portal.jdbc.mapper.QueryParamMapper;
import org.exframework.portal.metadata.queryparam.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rwe
 * @since Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class PortalCoreQueryParamDaoImpl extends PortalCoreMetaDaoImpl<QueryParamDO,QueryParam> implements PortalCoreQueryParamDao {
	
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

	@Override
	public QueryParam transform(QueryParamDO queryParamDO) {
		return queryParamDO;
	}


}
