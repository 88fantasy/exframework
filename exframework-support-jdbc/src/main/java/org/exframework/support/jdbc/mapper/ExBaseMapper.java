package org.exframework.support.jdbc.mapper;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.annotations.Param;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.common.entity.Pager;
import org.exframework.support.common.util.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author rwe Date: Jan 5, 2021
 * <p>
 * Copyright @ 2021
 */
public interface ExBaseMapper<T> extends BaseMapper<T> {

    default QueryWrapper<T> wrapperFromCondition(Collection<FilterCondition> conditions) {
        return wrapperFromConditionAndSort(conditions, Collections.emptyList());
    }

    default QueryWrapper<T> wrapperFromConditionAndSort(FilterCondition[] conditions, String[] sorts) {
        return wrapperFromConditionAndSort(Arrays.asList(conditions), Arrays.asList(sorts));
    }

    default QueryWrapper<T> wrapperFromConditionAndSort(Collection<FilterCondition> conditions, Collection<String> sorts) {
        if (CollUtil.isEmpty(conditions) && CollUtil.isEmpty(sorts)) {
            return Wrappers.query();
        }
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (!CollUtil.isEmpty(conditions)) {
            for (FilterCondition fc : conditions) {
                if (fc.getFilterValue() == null) {
                    continue;
                } else if (fc.getFilterDataType() == null) {
                    fc.setFilterDataType(FilterCondition.defaultType(fc.getFilterValue()));
                }

                String key = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(fc.getKey());
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
                                if (Collection.class.isAssignableFrom(fv.getClass())) {
                                    Collection<?> value = (Collection<?>) fv;
                                    if (value.size() > 0) {
                                        wrapper.in(key, value);
                                    } else {
                                        emptyWapper(wrapper);
                                    }
                                } else if (fv.getClass().isArray()) {
                                    Object[] value = (Object[]) fv;
                                    if (value.length > 0) {
                                        wrapper.in(key, Arrays.asList(value));
                                    } else {
                                        emptyWapper(wrapper);
                                    }
                                }
                                break;
                            default:
                                break;

                        }
                        break;
                    case NOT_IN:
                        switch (fc.getFilterDataType()) {
                            case LIST:
                                Object fv = fc.getFilterValue();
                                if (Collection.class.isAssignableFrom(fv.getClass())) {
                                    Collection<?> value = (Collection<?>) fv;
                                    if (value.size() > 0) {
                                        wrapper.notIn(key, value);
                                    } else {
                                        emptyWapper(wrapper);
                                    }
                                } else if (fv.getClass().isArray()) {
                                    Object[] value = (Object[]) fv;
                                    if (value.length > 0) {
                                        wrapper.notIn(key, Arrays.asList(value));
                                    } else {
                                        emptyWapper(wrapper);
                                    }
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
        }
        sort(wrapper, sorts);
        return wrapper;
    }

    default void sort(QueryWrapper<T> wrapper, Collection<String> sorts) {
        if (!CollUtil.isEmpty(sorts)) {
            for (String sort : sorts) {
                if (StringUtils.hasLength(sort)) {
                    String trim = com.baomidou.mybatisplus.core.toolkit.StringUtils.isCamel(sort.trim()) ? com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(sort.trim()) : sort.trim();
                    String key = trim.substring(0, sort.length());
                    String opera = trim.substring(sort.length());

                    if ("+".equals(opera)) {
                        wrapper.orderByAsc(key);
                    } else if ("-".equals(opera)) {
                        wrapper.orderByDesc(key);
                    } else {
                        wrapper.orderByDesc(trim);
                    }
                }
            }
        }
    }

    default <E> PageModel<E> modelFromPage(Page<T> page, Class<E> clazz) {
        return modelFromPage(page, getTranslator(clazz));
    }

    default <E> PageModel<E> modelFromPage(Page<T> page, Function<T, E> translator) {
        PageModel<T> model = new PageModel<>(
                new Pager(page.getTotal(), new org.exframework.support.common.entity.Page(page.getCurrent(), page.getSize())),
                page.getRecords());
        List<T> tlist = model.getList();
        List<E> elist = tlist.stream().map(translator).collect(Collectors.toList());
        return new PageModel<>(model.getPager(), elist);
    }

    default <E> PageModel<E> query(FilterCondition[] params, org.exframework.support.common.entity.Page page,
                                   Class<E> clazz) {
        return query(!ArrayUtils.isEmpty(params) ? Arrays.asList(params) : Collections.emptyList(), page, Collections.emptyList(), getTranslator(clazz));
    }

    default <E> PageModel<E> query(FilterCondition[] params, org.exframework.support.common.entity.Page page, String[] sorts,
                                   Class<E> clazz) {
        return query(!ArrayUtils.isEmpty(params) ? Arrays.asList(params) : Collections.emptyList(), page, !ArrayUtils.isEmpty(sorts) ? Arrays.asList(sorts) : Collections.emptyList(), getTranslator(clazz));
    }

    default <E> PageModel<E> query(FilterCondition[] params, org.exframework.support.common.entity.Page page,
                                   Function<T, E> translator, Class<E> clazz) {
        return query(!ArrayUtils.isEmpty(params) ? Arrays.asList(params) : Collections.emptyList(), page, Collections.emptyList(), translator);
    }

    default <E> PageModel<E> query(FilterCondition[] params, org.exframework.support.common.entity.Page page, String[] sorts,
                                   Function<T, E> translator, Class<E> clazz) {
        return query(!ArrayUtils.isEmpty(params) ? Arrays.asList(params) : Collections.emptyList(), page, !ArrayUtils.isEmpty(sorts) ? Arrays.asList(sorts) : Collections.emptyList(), translator);
    }

    default <E> PageModel<E> query(Collection<FilterCondition> params, org.exframework.support.common.entity.Page page,
                                   Class<E> clazz) {
        return query(params, page, Collections.emptyList(), getTranslator(clazz));
    }

    default <E> PageModel<E> query(Collection<FilterCondition> params, org.exframework.support.common.entity.Page page,
                                   Collection<String> sorts, Class<E> clazz) {
        return query(params, page, sorts, getTranslator(clazz));
    }

    default <E> PageModel<E> query(Collection<FilterCondition> params, org.exframework.support.common.entity.Page page,
                                   Collection<String> sorts, Function<T, E> translator) {
        return query(page, wrapperFromConditionAndSort(params, sorts), translator);
    }

    default PageModel<T> query(org.exframework.support.common.entity.Page page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        Page<T> p = selectPage(new Page<>(page.getCurrent(), page.getPageSize()), queryWrapper);
        PageModel<T> model = new PageModel<>(
                new Pager(p.getTotal(), new org.exframework.support.common.entity.Page(p.getCurrent(), p.getSize())),
                p.getRecords());
        List<T> tlist = model.getList();
        return new PageModel<>(model.getPager(), tlist);
    }

    default <E> PageModel<E> query(org.exframework.support.common.entity.Page page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper,
                                   Function<T, E> translator) {
        Page<T> p = selectPage(new Page<>(page.getCurrent(), page.getPageSize()), queryWrapper);
        return modelFromPage(p, translator);
    }

    default <E> List<E> list(Collection<FilterCondition> params, Class<E> clazz) {
        return list(params, Collections.emptyList(), getTranslator(clazz));
    }

    default <E> List<E> list(Collection<FilterCondition> params, Collection<String> sorts, Class<E> clazz) {
        return list(params, sorts, getTranslator(clazz));
    }

    default <E> List<E> list(Collection<FilterCondition> params, Collection<String> sorts, Function<T, E> translator) {
        return list(wrapperFromConditionAndSort(params, sorts), translator);
    }

    default <E> List<E> list(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper, Function<T, E> translator) {
        List<T> t = selectList(queryWrapper);
        return t.stream().map(translator).collect(Collectors.toList());
    }

    default <E> Function<T, E> getTranslator(Class<E> clazz) {
        return entity -> BeanUtils.copyTo(entity, clazz);
    }

    default QueryWrapper<T> emptyWapper(QueryWrapper<T> wapper) {
        return wapper.apply(" 1 = 2 ");
    }
}
