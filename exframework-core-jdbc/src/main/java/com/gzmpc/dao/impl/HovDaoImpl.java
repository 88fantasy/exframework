package com.gzmpc.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.core.entity.HovDO;
import com.gzmpc.core.mapper.HovMapper;
import com.gzmpc.dao.HovDao;
import com.gzmpc.metadata.hov.Hov;

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
