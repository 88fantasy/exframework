package org.exframework.portal.enums;

import org.exframework.support.common.enums.DictionaryEnum;

/**
 * 操作符枚举
 *
 * @author rwe
 * @date 2021/10/8 21:38
 **/
public enum FilterConditionOper implements DictionaryEnum {

    EQUAL("等于"), GREATER("大于"), LESS("小于"), BETWEEN("介于"),
    GREATER_EQUAL("大于等于"), LESS_EQUAL("小于等于"), IN("包含"),
    MATCHING("匹配"), NOT_EQUAL("不等于"), ISNULL("为空"),
    IS_NOT_NULL("不为空"), STR("自定义");


    private String label;

    private FilterConditionOper(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}