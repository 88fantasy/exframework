package org.exframework.portal.metadata.dict;

/**
 * 字典值对
 *
 * @author rwe
 * @date 2021/8/20 16:19
 **/
public class DictionaryItemValue {

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public DictionaryItemValue setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DictionaryItemValue setValue(String value) {
        this.value = value;
        return this;
    }

}
