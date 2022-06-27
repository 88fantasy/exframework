package org.exframework.portal.metadata.dict;

import org.exframework.portal.service.sys.PortalCoreDdlService;
import org.exframework.support.common.annotation.Dictionary;
import org.exframework.support.common.enums.DictionaryEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 加载EntityClass注解
 *
 * @author rwe
 * @since Jan 11, 2021
 * <p>
 * Copyright @ 2021
 */
//@Repository
public class DictionaryUpdateListener implements ApplicationListener<ApplicationReadyEvent> {

    private Logger log = LoggerFactory.getLogger(DictionaryUpdateListener.class.getName());

    @Autowired
    PortalCoreDdlService portalCoreDdlService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext ac = event.getApplicationContext();
        log.info("开始更新字典" + Dictionary.class.getName());
        ;
        for (Map.Entry<String, Class<Enum<?>>> entry : Dictionary.DictionaryCache.entrySet()) {
            Class<Enum<?>> clazz = entry.getValue();
            Dictionary d = clazz.getAnnotation(Dictionary.class);
            String dictName = d.name();
            String dictCode = d.value();
            if (!StringUtils.hasText(dictCode)) {
                String simpleName = clazz.getSimpleName();
                dictCode = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
            }
            log.info(MessageFormat.format("加载字典{0}", dictCode));
            Enum<?>[] enums = clazz.getEnumConstants();
            List<DictionaryItemValue> values = Stream.of(enums).sorted(Comparator.comparingInt(Enum::ordinal)).map(ee -> {
                DictionaryItemValue value = new DictionaryItemValue()
                        .setKey(ee.name())
                        .setValue(ee.name());
                if (DictionaryEnum.class.isAssignableFrom(ee.getClass())) {
                    DictionaryEnum e = (DictionaryEnum) ee;
                    value.setValue(e.getLabel());
                }
                return value;
            }).collect(Collectors.toList());

            portalCoreDdlService.saveDictionary(dictCode, dictName, values, true);
        }
    }
}
