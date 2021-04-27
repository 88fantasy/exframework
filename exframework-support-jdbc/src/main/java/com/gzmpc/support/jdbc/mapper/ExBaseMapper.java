package com.gzmpc.support.jdbc.mapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzmpc.support.common.entity.FilterCondition;
import com.gzmpc.support.common.entity.PageModel;
import com.gzmpc.support.common.entity.Pager;
import com.gzmpc.support.common.util.BeanUtils;
import com.gzmpc.support.common.util.StringUtils;

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
			return Wrappers.emptyWrapper();
		}
		QueryWrapper<T> wrapper = new QueryWrapper<T>();
		for (FilterCondition fc : conditions) {
			if (fc.getFilterValue() == null) {
				continue;
			}
			else if(fc.getFilterDataType() == null) {
				fc.setFilterDataType(FilterCondition.defaultType(fc.getFilterValue()));
			}
			
			String key = StringUtils.humpToUnderline(fc.getKey());
			switch (fc.getOper()) {
			case BETWEEN:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					List<?> values = (List<?>) fc.getFilterValue();
					if (values != null && values.size() == 2) {
						wrapper.between(key, values.get(0), values.get(1));
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
					wrapper.eq(key, fc.getFilterValue());
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
					wrapper.ne(key, fc.getFilterValue());
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
					wrapper.gt(key, fc.getFilterValue());
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
					wrapper.ge(key, fc.getFilterValue());
					break;
				default:
					break;
				}
				break;
			case IN:
				switch (fc.getFilterDataType()) {
				case LIST:
					Object fv = fc.getFilterValue();
					if ( Collection.class.isAssignableFrom(fv.getClass())) {
						Collection<?> value = (Collection<?>) fv;
						wrapper.in(key, value);
					}
					else {
						wrapper.in(key, Arrays.asList(fv));
					}
					break;
				default:
					break;

				}
				break;
			case ISNULL:
				wrapper.isNull(key);
				break;
			case IS_NOT_NULL:
				wrapper.isNotNull(key);
				break;
			case LESS:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					wrapper.lt(key, fc.getFilterValue());
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
					wrapper.le(key, fc.getFilterValue());
					break;
				default:
					break;
				}
				break;
			case MATCHING:
				switch (fc.getFilterDataType()) {
				case STRING:
					if (fc.getFilterValue() instanceof String) {
						wrapper.like(key, fc.getFilterValue());
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
		return modelFromPage(page, getTranslator(clazz), clazz);
	}
	
	default <E> PageModel<E> modelFromPage(Page<T> page, Function<T,E> translator, Class<E> clazz) {
		PageModel<T> model = new PageModel<T>(
				new Pager(page.getTotal(), new com.gzmpc.support.common.entity.Page(page.getCurrent(), page.getSize())),
				page.getRecords());
		List<T> tlist = model.getList();
		List<E> elist = tlist.stream().map(translator).collect(Collectors.toList());
		return new PageModel<E>(model.getPager(), elist);
	}

	default <E> PageModel<E> query(FilterCondition[] params, com.gzmpc.support.common.entity.Page page,
			Class<E> clazz) {
		return query(Arrays.asList(params), page, getTranslator(clazz), clazz);
	}
	
	default <E> PageModel<E> query(FilterCondition[] params, com.gzmpc.support.common.entity.Page page,
			Function<T,E> translator, Class<E> clazz) {
		return query(Arrays.asList(params), page, translator, clazz);
	}
	
	default <E> PageModel<E> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page,
			Class<E> clazz) {
		return query(params, page, getTranslator(clazz), clazz);
	}
	
	default <E> PageModel<E> query(Collection<FilterCondition> params, com.gzmpc.support.common.entity.Page page,
			Function<T,E> translator, Class<E> clazz) {
		return query(page, wrapperFromCondition(params), translator, clazz);
	}
	
	default PageModel<T> query(com.gzmpc.support.common.entity.Page page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
		Page<T> p = selectPage(new Page<T>(page.getCurrent(), page.getPageSize()), queryWrapper);
		PageModel<T> model = new PageModel<T>(
				new Pager(p.getTotal(), new com.gzmpc.support.common.entity.Page(p.getCurrent(), p.getSize())),
				p.getRecords());
		List<T> tlist = model.getList();
		return new PageModel<>(model.getPager(), tlist);
	}
	
	default <E> PageModel<E> query(com.gzmpc.support.common.entity.Page page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper,
			Function<T,E> translator, Class<E> clazz) {
		Page<T> p = selectPage(new Page<T>(page.getCurrent(), page.getPageSize()), queryWrapper);
		return modelFromPage(p, translator, clazz);
	}

	default <E> List<E> list(Collection<FilterCondition> params, Class<E> clazz) {
		return list(params, getTranslator(clazz), clazz);
	}
	
	default <E> List<E> list(Collection<FilterCondition> params, Function<T,E> translator, Class<E> clazz) {
		return list(wrapperFromCondition(params),translator, clazz);
	}
	
	default <E> List<E> list(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper, Function<T,E> translator, Class<E> clazz) {
		List<T> t = selectList(queryWrapper);
		return t.stream().map(getTranslator(clazz)).collect(Collectors.toList());
	}
	
	default <E> Function<T,E> getTranslator(Class<E> clazz) {
		return entity -> BeanUtils.copyTo(entity, clazz);
	}
}
