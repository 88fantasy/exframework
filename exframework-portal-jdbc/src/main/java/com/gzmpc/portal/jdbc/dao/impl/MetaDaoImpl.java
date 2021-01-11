package com.gzmpc.portal.jdbc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzmpc.portal.dao.MetaDao;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.Meta;

/**
 *
 * Author: rwe
 * Date: Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public abstract class MetaDaoImpl<U extends T,T extends Meta> implements MetaDao<T> {

	public abstract BaseMapper<U> getBaseMapper();
	
	public abstract U genInstance();
	
	public Collection<String> allKeys() {
		return all().stream().map(T::getCode).collect(Collectors.toList());
	}
	
	public Collection<T> all() {
		return new ArrayList<T>(getBaseMapper().selectList(null));
	}
	
	@Nullable
	public T findByKey(String code) {
		U u = getBaseMapper().selectById(code);
		return u;
	}
	
	public T findByKeyWithException(String code) throws NotFoundException {
		U u = getBaseMapper().selectById(code);
		if( u == null) {
			throw new NotFoundException();
		}
		return u;
	}
	
	public U copy(T entity, U u)  {
		BeanUtils.copyProperties(entity, u);
		return u;
	}
	
	public int insert(T entity) {
		return getBaseMapper().insert(copy(entity,genInstance()));
	}
	
	public int update(T entity) {
		return getBaseMapper().updateById(copy(entity,genInstance()));
	}
	
	public int remove(String code) {
		return getBaseMapper().deleteById(code);
	}
}
