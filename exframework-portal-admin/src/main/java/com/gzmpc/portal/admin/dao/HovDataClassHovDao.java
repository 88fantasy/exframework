package com.gzmpc.portal.admin.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.gzmpc.portal.metadata.hov.HovDataClass;
import com.gzmpc.portal.metadata.hov.IHovDao;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.common.entity.Pager;

/**
 *	参照实现类 Hov
 * Author: rwe Date: 2021年3月18日
 *
 * Copyright @ 2021
 * 
 */
@Repository
public class HovDataClassHovDao implements IHovDao<HovDataClass> {

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public Class<HovDataClass> getEntityClass() {
		return HovDataClass.class;
	}

	@Override
	public PageModel<HovDataClass> query(Collection<FilterCondition> conditions, Page page) {
		Map<String, IHovDao> beans = applicationContext.getBeansOfType(IHovDao.class);
		Map<String,String> fcmap = conditions.stream().collect(Collectors.toMap(FilterCondition::getKey, fc -> (String)fc.getFilterValue()));
		List<HovDataClass> list = beans.values().stream()
				.filter(c -> {
					boolean filtered = true;
					if(fcmap.containsKey("className")) {
						filtered =  filtered && c.getClass().getName().indexOf(fcmap.get("className")) != -1;
					}
					if(fcmap.containsKey("entityClass")) {
						filtered = filtered && c.getClass().getName().indexOf(fcmap.get("entityClass")) != -1;
					}
					return filtered;
				})
				.map(c -> new HovDataClass(c.getEntityClass().getName(), c.getClass().getName())).collect(Collectors.toList());
		if(list.size() > 0 && page.getCurrent() * page.getPageSize() > list.size() ) {
			Long count = new Long(list.size());
			Long from = (page.getCurrent() - 1) * page.getPageSize();
			Long to = page.getCurrent() * page.getPageSize();
			List<HovDataClass> newList = list.subList(from.intValue(), (to > count ? count.intValue() : to.intValue()));
			return new PageModel<HovDataClass>(new Pager(count, page), newList) ;
		}
		else {
			return new PageModel<HovDataClass>(new Pager(new Long(list.size()), page), list) ;
		}
	}

}
