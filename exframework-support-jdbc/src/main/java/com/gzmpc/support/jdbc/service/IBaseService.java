package com.gzmpc.support.jdbc.service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzmpc.support.jdbc.exception.SessionExcetpion;

/**
 *
 * Author: rwe
 * Date: Jan 6, 2021
 *
 * Copyright @ 2021 
 * 
 */
public abstract class IBaseService<T> implements IService<T> {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveBatch(Collection<T> entityList, int batchSize) {
		for(T entity : entityList) {
			int success = getBaseMapper().insert(entity);
			if(success < 1) {
				throw new SessionExcetpion("插入数据失败");
			}
		}
		return true;
	}

	@Override
	public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(Collection<T> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
		// TODO Auto-generated method stub
		return null;
	}

}
