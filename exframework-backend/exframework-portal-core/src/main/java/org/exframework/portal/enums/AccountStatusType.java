package org.exframework.portal.enums;

import org.exframework.support.common.enums.DictionaryEnum;

/**
 * 用户状态
 *
 * @author rwe
 * @date 2021/10/8 21:27
 **/
public enum AccountStatusType implements DictionaryEnum {

    /**
     * 有效
     */
    VALID("有效"),

    /**
     * 失效
     */
    INVALID("失效"),

    /**
     * 禁止
     */
    FORBIDDEN("禁止")

    ;

    private String label;

    private AccountStatusType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

}