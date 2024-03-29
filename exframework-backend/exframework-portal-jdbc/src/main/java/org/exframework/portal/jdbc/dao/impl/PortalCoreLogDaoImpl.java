package org.exframework.portal.jdbc.dao.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.exframework.portal.dao.PortalCoreLogDao;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.jdbc.entity.system.LogDO;
import org.exframework.portal.jdbc.mapper.LogMapper;
import org.exframework.portal.metadata.sys.Logger;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 日志数据类
 * @author rwe
 * @since Jan 19, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Repository
public class PortalCoreLogDaoImpl implements PortalCoreLogDao {
	
	@Autowired
	LogMapper logMapper;

	@Override
	public Logger findByKey(Long loggerId) throws NotFoundException {
		return logMapper.selectOne(Wrappers.lambdaQuery(LogDO.class).eq(LogDO::getLoggerId, loggerId));
	}

	@Override
	public int insert(Logger entity) {
//		logMapper.insert(entity)
		return 0;
	}

	@Override
	public PageModel<Logger> query(Collection<FilterCondition> params, org.exframework.support.common.entity.Page page) {
		return logMapper.query(params, page, Logger.class);
	}

	@Override
	public List<Logger> list(Collection<FilterCondition> params) {
		return logMapper.list(params, Logger.class);
	}

}
