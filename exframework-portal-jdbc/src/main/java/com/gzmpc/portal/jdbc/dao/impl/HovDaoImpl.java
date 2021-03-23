package com.gzmpc.portal.jdbc.dao.impl;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.portal.dao.HovDao;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.portal.jdbc.entity.HovDO;
import com.gzmpc.portal.jdbc.mapper.HovMapper;

/**
 * 参照数据类
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Repository
public class HovDaoImpl extends MetaDaoImpl<HovDO,Hov> implements HovDao {
	
	@Autowired
	HovMapper hovMapper;

	@Override
	public BaseMapper<HovDO> getBaseMapper() {
		return hovMapper;
	}

	@Override
	public HovDO genInstance() {
		return new HovDO();
	}

	@Override
	public PageModel<Hov> query(Collection<FilterCondition> params, Page page) {
		return hovMapper.query(params, page, Hov.class);
	}

	@Override
	public List<Hov> list(Collection<FilterCondition> params) {
		return hovMapper.list(params, Hov.class);
	}


}
