package org.exframework.support.elasticsearch.repository;
//package org.exframework.support.elasticsearch.repository;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchOperations;
//import org.springframework.data.elasticsearch.core.query.Criteria;
//import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.repository.NoRepositoryBean;
//
//import org.exframework.support.common.entity.FilterCondition;
//import org.exframework.support.common.entity.PageModel;
//import org.exframework.support.common.entity.Pager;
//import org.exframework.support.common.util.BeanUtils;
//import org.exframework.support.common.util.StringUtils;
//
///**
// *
// * @author rwe
// * @since 2021年3月3日
// *
// * Copyright @ 2021 
// * 
// */
//@NoRepositoryBean
//public interface ExElasticsearchRepository<T, ID> extends ElasticsearchRepository<T, ID> {
//	
//	ElasticsearchRestTemplate getElasticsearchRestTemplate();
//
//	default Criteria wrapperFromCondition(Collection<FilterCondition> conditions) {
//		if (conditions == null || conditions.size() == 0) {
//			return null;
//		}
//		
//		for (FilterCondition fc : conditions) {
//			if (fc.getFilterValue() == null) {
//				continue;
//			}
//			else if(fc.getFilterDataType() == null) {
//				fc.setFilterDataType(FilterCondition.defaultType(fc.getFilterValue()));
//			}
//			
//			String key = StringUtils.humpToUnderline(fc.getKey());
//			switch (fc.getOper()) {
//			case BETWEEN:
//				switch (fc.getFilterDataType()) {
//				case DATE:
//				case DATETIME:
//				case NUMBER:
//					List<?> values = (List<?>) fc.getFilterValue();
//					if (values != null && values.size() == 2) {
//						wrapper.between(key, values.get(0), values.get(1));
//					}
//					break;
//				default:
//					break;
//				}
//				break;
//			case EQUAL:
//				switch (fc.getFilterDataType()) {
//				case DATE:
//				case DATETIME:
//				case NUMBER:
//				case STRING:
//					wrapper.eq(key, fc.getFilterValue());
//					break;
//				default:
//					break;
//
//				}
//				break;
//			case NOT_EQUAL:
//				switch (fc.getFilterDataType()) {
//				case DATE:
//				case DATETIME:
//				case NUMBER:
//				case STRING:
//					wrapper.ne(key, fc.getFilterValue());
//					break;
//				default:
//					break;
//
//				}
//				break;
//			case GREATER:
//				switch (fc.getFilterDataType()) {
//				case DATE:
//				case DATETIME:
//				case NUMBER:
//					wrapper.gt(key, fc.getFilterValue());
//					break;
//				default:
//					break;
//				}
//				break;
//			case GREATER_EQUAL:
//				switch (fc.getFilterDataType()) {
//				case DATE:
//				case DATETIME:
//				case NUMBER:
//					wrapper.ge(key, fc.getFilterValue());
//					break;
//				default:
//					break;
//				}
//				break;
//			case IN:
//				switch (fc.getFilterDataType()) {
//				case LIST:
//					if (fc.getFilterValue() instanceof Collection) {
//						wrapper.in(key, fc.getFilterValue());
//					}
//					break;
//				default:
//					break;
//
//				}
//				break;
//			case ISNULL:
//				wrapper.isNull(key);
//				break;
//			case IS_NOT_NULL:
//				wrapper.isNotNull(key);
//				break;
//			case LESS:
//				switch (fc.getFilterDataType()) {
//				case DATE:
//				case DATETIME:
//				case NUMBER:
//					wrapper.lt(key, fc.getFilterValue());
//					break;
//				default:
//					break;
//				}
//				break;
//			case LESS_EQUAL:
//				switch (fc.getFilterDataType()) {
//				case DATE:
//				case DATETIME:
//				case NUMBER:
//					wrapper.le(key, fc.getFilterValue());
//					break;
//				default:
//					break;
//				}
//				break;
//			case MATCHING:
//				switch (fc.getFilterDataType()) {
//				case STRING:
//					if (fc.getFilterValue() instanceof String) {
//						wrapper.like(key, fc.getFilterValue());
//					}
//					break;
//				default:
//					break;
//				}
//				break;
//			case STR:
//				switch (fc.getFilterDataType()) {
//				case STRING:
//					if (fc.getFilterValue() instanceof String) {
//						String value = (String) fc.getFilterValue();
//						wrapper.apply(value);
//					}
//					break;
//				default:
//					break;
//
//				}
//				break;
//			default:
//				break;
//
//			}
//		}
//		return wrapper;
//	}
//
//	default <E> PageModel<E> modelFromPage(Page<T> page, Class<E> clazz) {
//		PageModel<T> model = new PageModel<T>(
//				new Pager(page.getTotalElements(), new org.exframework.support.common.entity.Page(page.getNumber(), page.getNumberOfElements())),
//				page.getContent());
//		return model.copy(clazz);
//	}
//
//	default <E> PageModel<E> query(Collection<FilterCondition> params, org.exframework.support.common.entity.Page page,
//			Class<E> clazz) {
//		Pageable pageable = PageRequest.of(page.getCurrent().intValue(), page.getPageSize().intValue());
//		wrapperFromCondition(params);
//		CriteriaQuery query = new CriteriaQuery(criteria, pageable);
//		getElasticsearchRestTemplate().search(query, clazz);
//		Page<T> p = search(query);
//		return modelFromPage(p, clazz);
//	}
//
//	default <E> List<E> list(Collection<FilterCondition> params, Class<E> clazz) {
//		List<T> t = selectList(wrapperFromCondition(params));
//		return t.stream().map(row -> {
//			return BeanUtils.copyTo(row, clazz);
//		}).collect(Collectors.toList());
//	}
//}
