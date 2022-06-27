package org.exframework.portal.metadata.dict;

import org.exframework.support.common.annotation.Dictionary;
import org.exframework.support.common.enums.DictionaryEnum;
import org.exframework.support.common.util.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字典注解数据源
 *
 * @author rwe
 * @date 2022/5/15 16:12
 **/
@Repository
public class AnnotationDictionaryDataSource implements DictionaryDataSource {

    private final static Logger logger = LoggerFactory.getLogger(AnnotationDictionaryDataSource.class);

    private final static String DATA_SOURCE_TYPE = "annotation";

    @Override
    public String dataSourceType() {
        return DATA_SOURCE_TYPE;
    }

    @Override
    public Collection<String> keys() {
        return Dictionary.DictionaryCache.entrySet().stream().map(entry -> getKey(entry.getValue())).collect(Collectors.toList());
    }

    @Override
    public List<DictionaryItemValue> getValue(String key) {
        Optional<Map.Entry<String, Class<Enum<?>>>> optional = Dictionary.DictionaryCache.entrySet().stream().filter(entry -> key.equals(getKey(entry.getValue()))).findAny();
        if (!optional.isPresent()) {
            return Collections.emptyList();
        }
        Enum<?>[] enums = optional.get().getValue().getEnumConstants();
        return Stream.of(enums).sorted(Comparator.comparingInt(Enum::ordinal)).map(ee -> {
            DictionaryItemValue value = new DictionaryItemValue()
                    .setKey(ee.name())
                    .setValue(ee.name());
            if (DictionaryEnum.class.isAssignableFrom(ee.getClass())) {
                DictionaryEnum e = (DictionaryEnum) ee;
                value.setValue(e.getLabel());
            }
            return value;
        }).collect(Collectors.toList());
    }

    private String getKey(Class<Enum<?>> clazz) {
        Dictionary d = clazz.getAnnotation(Dictionary.class);
        String dictCode = d.value();
        if (!StringUtils.hasText(dictCode)) {
            dictCode = StrUtils.humpToUnderline(clazz.getSimpleName());
        }
        logger.info(MessageFormat.format("加载字典{0}", dictCode));
        return dictCode;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
