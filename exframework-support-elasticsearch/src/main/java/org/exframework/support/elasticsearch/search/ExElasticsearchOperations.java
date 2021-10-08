package org.exframework.support.elasticsearch.search;

import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.common.entity.Pager;
import org.exframework.support.common.util.BeanUtils;
import org.exframework.support.common.util.StrUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rwe
 * @since 2021年3月3日
 *
 * Copyright @ 2021 
 * 
 */

public interface ExElasticsearchOperations extends ElasticsearchOperations {

	default Criteria criteriaFromCondition(Collection<FilterCondition> conditions) {
		if (conditions == null || conditions.size() == 0) {
			return null;
		}
		Criteria c = Criteria.and();
		for (FilterCondition fc : conditions) {
			if (fc.getFilterValue() == null) {
				continue;
			}
			else if(fc.getFilterDataType() == null) {
				fc.setFilterDataType(FilterCondition.defaultType(fc.getFilterValue()));
			}
			
			String key = StrUtils.humpToUnderline(fc.getKey());
			switch (fc.getOper()) {
			case BETWEEN:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					List<?> values = (List<?>) fc.getFilterValue();
					if (values != null && values.size() == 2) {
						c.and(new Criteria(key).between(values.get(0), values.get(1)));
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
					c.and(new Criteria(key).is(fc.getFilterValue()));
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
					c.and(new Criteria(key).not().is(fc.getFilterValue()));
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
					c.and(new Criteria(key).greaterThan(fc.getFilterValue()));
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
					c.and(new Criteria(key).greaterThanEqual(fc.getFilterValue()));
					break;
				default:
					break;
				}
				break;
			case IN:
				switch (fc.getFilterDataType()) {
				case LIST:
					if (fc.getFilterValue() instanceof Collection) {
						c.and(new Criteria(key).in(key, fc.getFilterValue()));
					}
					break;
				default:
					break;

				}
				break;
			case ISNULL:
//				c.and(new Criteria(key).isNull(key);
				break;
			case IS_NOT_NULL:
//				wrapper.isNotNull(key);
				break;
			case LESS:
				switch (fc.getFilterDataType()) {
				case DATE:
				case DATETIME:
				case NUMBER:
					c.and(new Criteria(key).lessThan(fc.getFilterValue()));
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
					c.and(new Criteria(key).lessThanEqual(fc.getFilterValue()));
					break;
				default:
					break;
				}
				break;
			case MATCHING:
				switch (fc.getFilterDataType()) {
				case STRING:
					if (fc.getFilterValue() instanceof String) {
						c.and(new Criteria(key).matches(fc.getFilterValue()));
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
//						wrapper.apply(value);
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
		return c;
	}

	default <E, T> PageModel<E> modelFromPage(SearchPage<T> page, Class<T> documentClass, Class<E> viewClass) {
		List<T> list = page.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
		PageModel<T> model = new PageModel<T>(
				new Pager(page.getTotalElements(), new org.exframework.support.common.entity.Page(page.getNumber(), page.getNumberOfElements())),
				list);
		return model.copy(viewClass);
	}

	default <T, E> PageModel<E> query(Collection<FilterCondition> params, org.exframework.support.common.entity.Page page,
			Class<T> documentClass, Class<E> viewClass) {
		Pageable pageable = PageRequest.of(page.getCurrent().intValue(), page.getPageSize().intValue());
		CriteriaQuery query = new CriteriaQuery(criteriaFromCondition(params), pageable);
		SearchHits<T> hits = search(query, documentClass);
		SearchPage<T> searchPage = SearchHitSupport.searchPageFor(hits, pageable);
		return modelFromPage(searchPage, documentClass, viewClass);
	}

	default <E,T> List<E> list(Collection<FilterCondition> params, Class<T> documentClass, Class<E> viewClass) {
		CriteriaQuery query = new CriteriaQuery(criteriaFromCondition(params));
		return searchForStream(query, documentClass).stream().map(row -> {
			return BeanUtils.copyTo(row.getContent(), viewClass);
		}).collect(Collectors.toList());
	}
}
