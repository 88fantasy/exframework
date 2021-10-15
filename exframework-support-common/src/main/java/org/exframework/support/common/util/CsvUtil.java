package org.exframework.support.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvRow;
import org.exframework.support.common.annotation.CsvField;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Csv 工具类
 *
 * @author rwe
 * @date 2021/10/15 15:54
 **/
public class CsvUtil extends cn.hutool.core.text.csv.CsvUtil {

    /**
     * 从 bean 生成 csv 数据
     *
     * @param beans 数据组
     * @return csv 数据
     */
    public static CsvData dataFromBeans(Collection<?> beans) {
        if (ObjectUtils.isEmpty(beans)) {
            return new CsvData(Collections.emptyList(), Collections.emptyList());
        }
        Map<String, CsvField> fieldMap = new ConcurrentHashMap<>();
        List<CsvRow> rows = new ArrayList<>();
        Optional<?> optionalObject = beans.stream().findFirst();
        if (!optionalObject.isPresent()) {
            return new CsvData(Collections.emptyList(), Collections.emptyList());
        }
        Object bean = optionalObject.get();
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            CsvField csvField = field.getAnnotation(CsvField.class);
            fieldMap.put(field.getName(), csvField);
        }, field -> field.isAnnotationPresent(CsvField.class));

        List<String> headers = fieldMap.keySet().stream().sorted(Comparator.comparingInt(h -> fieldMap.get(h).order())).collect(Collectors.toList());
        for (int i = 0, j = beans.size(); i < j; i++) {
            Map<String, Object> beanMap = BeanUtil.beanToMap(bean);
            List<String> values = new ArrayList<>(headers.size());
            for (String field : headers) {
                Object value = beanMap.get(field);
                Class<? extends Function<Object, String>> clazz = fieldMap.get(field).translator();
                Function<Object, String> function = null;
                try {
                    function = SpringContextUtils.getBeanByClass(clazz);
                } catch (Exception ignored) {

                }
                if (function == null) {
                    try {
                        values.add(clazz.newInstance().apply(value));
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    values.add(function.apply(value));
                }
            }
            Map<String, Integer> headerMap = new ConcurrentHashMap<>(fieldMap.size());
            for (CsvField field : fieldMap.values()) {
                headerMap.put(field.value(), field.order());
            }
            rows.add(new CsvRow(i, headerMap, values));
        }
        return new CsvData(headers.stream().map(h -> fieldMap.get(h).value()).collect(Collectors.toList()), rows);
    }
}
