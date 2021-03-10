package com.gzmpc.portal.jdbc.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gzmpc.portal.dao.LogDao;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.jdbc.entity.LogDO;
import com.gzmpc.portal.jdbc.mapper.LogMapper;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.portal.metadata.sys.Logger;
import com.gzmpc.support.common.entity.PageModel;

/**
 * 日志数据类
 * Author: rwe
 * Date: Jan 19, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Repository
public class LogDaoImpl implements LogDao {
	
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
	public PageModel<Logger> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page) {
		return logMapper.query(params, page, Logger.class);
	}

	@Override
	public List<Logger> list(Collection<FilterCondition> params) {
		return logMapper.list(params, Logger.class);
	}

}
