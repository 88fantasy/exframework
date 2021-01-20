package com.gzmpc.portal.jdbc.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.common.entity.Pager;
import com.gzmpc.support.common.util.BeanUtil;

/**
 *
 * Author: rwe Date: Jan 5, 2021
 *
 * Copyright @ 2021
 * 
 */
public interface ExBaseMapper<T> extends BaseMapper<T> {

	default QueryWrapper<T> wrapperFromCondition(Collection<FilterCondition> conditions) {
		if (conditions == null || conditions.size() == 0) {
			return null;
		}
		QueryWrapper<T> wrapper = new QueryWrapper<T>();
		for (FilterCondition fc : conditions) {
			if (fc.getFilterValue() == null) {
				continue;
			}
			else if(fc.getFilterDataType() == null) {
				fc.setFilterDataType(FilterCondition.defaultType(fc.getFilterValue()));
			}
			switch (fc.getOper()) {
			case BETWEEN:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					List<?> values = (List<?>) fc.getFilterValue();
					if (values != null && values.size() == 2) {
						wrapper.between(fc.getKey(), values.get(0), values.get(1));
					}
					break;
				default:
					break;
				}
				break;
			case EQUAL:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
				case STRING:
					wrapper.eq(fc.getKey(), fc.getFilterValue());
					break;
				default:
					break;

				}
				break;
			case NOT_EQUAL:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
				case STRING:
					wrapper.ne(fc.getKey(), fc.getFilterValue());
					break;
				default:
					break;

				}
				break;
			case GREATER:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					wrapper.gt(fc.getKey(), fc.getFilterValue());
					break;
				default:
					break;
				}
				break;
			case GREATER_EQUAL:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					wrapper.ge(fc.getKey(), fc.getFilterValue());
					break;
				default:
					break;
				}
				break;
			case IN:
				switch (fc.getFilterDataType()) {
				case LIST:
					if (fc.getFilterValue() instanceof Collection) {
						wrapper.in(fc.getKey(), fc.getFilterValue());
					}
					break;
				default:
					break;

				}
				break;
			case ISNULL:
				wrapper.isNull(fc.getKey());
				break;
			case IS_NOT_NULL:
				wrapper.isNotNull(fc.getKey());
				break;
			case LESS:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					wrapper.lt(fc.getKey(), fc.getFilterValue());
					break;
				default:
					break;
				}
				break;
			case LESS_EQUAL:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					wrapper.le(fc.getKey(), fc.getFilterValue());
					break;
				default:
					break;
				}
				break;
			case MATCHING:
				switch (fc.getFilterDataType()) {
				case STRING:
					if (fc.getFilterValue() instanceof String) {
						wrapper.like(fc.getKey(), fc.getFilterValue());
					}
					break;
				default:
					break;
				}
				break;
			case STR:
				switch (fc.getFilterDataType()) {
				case STRING:
					if (fc.getFilterValue() instanceof String) {
						String value = (String) fc.getFilterValue();
						wrapper.apply(value);
					}
					break;
				default:
					break;

				}
				break;
			default:
				break;

			}
		}
		return wrapper;
	}

	default <E> PageModel<E> modelFromPage(Page<T> page, Class<E> clazz) {
		PageModel<T> model = new PageModel<T>(
				new Pager(page.getTotal(), new com.gzmpc.support.common.entity.Page(page.getCurrent(), page.getSize())),
				page.getRecords());
		return model.copy(clazz);
	}

	default <E> PageModel<E> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page,
			Class<E> clazz) {
		Page<T> p = selectPage(new Page<T>(page.getCurrent(), page.getPageSize()), wrapperFromCondition(params));
		return modelFromPage(p, clazz);
	}

	default <E> List<E> list(Collection<FilterCondition> params, Class<E> clazz) {
		List<T> t = selectList(wrapperFromCondition(params));
		return t.stream().map(row -> {
			return BeanUtil.copyTo(row, clazz);
		}).collect(Collectors.toList());
	}
}
