package org.exframework.portal.jdbc.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exframework.portal.dao.PortalCoreMetaDao;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.Meta;

/**
 *
 * @author rwe
 * @since Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public abstract class PortalCoreMetaDaoImpl<U,T extends Meta> implements PortalCoreMetaDao<T> {

	/**
	 * 获取关联 Mapper
	 * @return
	 */
	public abstract BaseMapper<U> getBaseMapper();

	/**
	 * 实例化Do
	 * @return
	 */
	public abstract U genInstance();

	/**
	 * Do 转换至 Entity
	 * @param u
	 * @return
	 */
	public abstract T transform(U u);
	
	@Override
	public Collection<String> allKeys() {
		return all().stream().map(T::getCode).collect(Collectors.toList());
	}
	
	@Override
	public Collection<T> all() {
		return new ArrayList<T>(getBaseMapper().selectList(null).stream().map(u -> transform(u)).collect(Collectors.toList()));
	}
	
	@Override
	@Nullable
	public T findByKey(String code) {
		U u = getBaseMapper().selectById(code);
		return transform(u);
	}
	
	public T findByKeyWithException(String code) throws NotFoundException {
		U u = getBaseMapper().selectById(code);
		if( u == null) {
			throw new NotFoundException();
		}
		return transform(u);
	}
	
	public U copy(T entity, U u)  {
		BeanUtils.copyProperties(entity, u);
		return u;
	}
	
	@Override
	public int insert(T entity) {
		return getBaseMapper().insert(copy(entity,genInstance()));
	}
	
	@Override
	public int update(T entity) {
		return getBaseMapper().updateById(copy(entity,genInstance()));
	}
	
	@Override
	public int remove(String code) {
		return getBaseMapper().deleteById(code);
	}
}
