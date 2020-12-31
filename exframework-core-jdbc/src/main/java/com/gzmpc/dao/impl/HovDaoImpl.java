package com.gzmpc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.core.entity.HovDO;
import com.gzmpc.core.mapper.HovMapper;
import com.gzmpc.dao.HovDao;
import com.gzmpc.exception.NotFoundException;
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
public class HovDaoImpl implements HovDao {
	
	@Autowired
	HovMapper hovMapper;

	@Override
	public Collection<String> allKeys() {
		return all().stream().map(Hov::getCode).collect(Collectors.toList());
	}

	@Override
	public Collection<Hov> all() {
		return new ArrayList<Hov>(hovMapper.selectList(null));
	}

	@Override
	public Hov findByKey(String key) throws NotFoundException {
		return hovMapper.selectOne(new QueryWrapper<HovDO>().eq("key", key));
	}

}
