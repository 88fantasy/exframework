package org.exframework.portal.enums;

import org.exframework.support.common.enums.DictionaryEnum;

/**
 * 数据项显示类型
 *
 * @author rwe
 * @date 2021/10/8 21:16
 **/
public enum DataItemDisplayType implements DictionaryEnum {
    /**
     * 输入
     */
    INPUT("输入框"),

    /**
     * 密码输入框
     */
    PASSWORD("密码输入框"),

    /**
     * 只读
     */
    READONLY("只读"),

    /**
     * 是否
     */
    CHECKBOX("是否"),

    /**
     * 字典
     */
    DICTIONARY("字典");

    private String label;

    DataItemDisplayType(String label) {
        this.label = label;
    }


    @Override
    public String getLabel() {
        return label;
    }

}