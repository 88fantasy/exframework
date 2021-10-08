package org.exframework.portal.enums;

import org.exframework.support.common.enums.DictionaryEnum;

/**
 * 数据项类型
 *
 * @author rwe
 * @date 2021/10/8 21:15
 **/
public enum DataItemValueType implements DictionaryEnum {

    /**
     * 默认 按 org.exframework.portal.dao.PortalCoreDataItemDao.defaultValueType
     */
    DEFAULT("默认"),

    /**
     * 整数
     */
    LONG("整数"),

    /**
     * 字符串
     */
    STRING("字符串"),

    /**
     * 大写字符串
     */
    UPPERSTRING("大写字符串"),

    /**
     * 小写字符串
     */
    LOWERSTRING("大写字符串"),

    /**
     * 小数
     */
    BIGDECIMAL("小数"),

    /**
     * 布尔
     */
    BOOLEAN("布尔"),

    /**
     * 日期
     */
    DATE("日期"),

    /**
     * 日期
     */
    DATETIME("日期时间")

    ;


    private String label;

    DataItemValueType(String label) {
        this.label = label;
    }


    @Override
    public String getLabel() {
        return label;
    }

}