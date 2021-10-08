package org.exframework.portal.enums;

import org.exframework.support.common.enums.DictionaryEnum;

/**
 * 查询条件枚举
 *
 * @author rwe
 * @date 2021/10/8 21:37
 **/
public enum FilterConditionDataType implements DictionaryEnum {

    STRING("字符串"),
    LIST("数组"),
    NUMBER("数字"),
    BOOLEAN("布尔"),
    JSON("JSON"),
    DATE("日期"),
    DATETIME("日期时间")
    ;

    private String label;

    FilterConditionDataType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}