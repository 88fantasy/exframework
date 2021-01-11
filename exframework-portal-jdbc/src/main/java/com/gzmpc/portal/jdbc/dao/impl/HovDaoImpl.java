package com.gzmpc.portal.jdbc.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.portal.dao.HovDao;
import com.gzmpc.portal.metadata.hov.Hov;
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


}
