package org.exframework.spring.boot.elasticsearch.repository;

import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.common.entity.Pager;
import org.exframework.support.common.util.BeanUtils;
import org.exframework.support.common.util.StrUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rwe
 * @since 2021年3月3日
 * <p>
 * Copyright @ 2021
 */
public interface ExElasticsearchRepository<T, ID> extends ElasticsearchRepository<T, ID> {

    ElasticsearchRestTemplate getElasticsearchRestTemplate();


}
