package org.exframework.portal.metadata.di;

import org.exframework.portal.enums.DataItemDisplayType;
import org.exframework.portal.enums.DataItemValueType;
import org.exframework.portal.metadata.Meta;
import org.exframework.portal.metadata.entity.EntityClass;

/**
 * 数据项
 */
@EntityClass
public class DataItem extends Meta {

    private static final long serialVersionUID = -6304054523810170909L;

    /**
     * 显示风格
     */
    @DataItemEntity(value = "DataItemDisplayType", name = "显示风格", type = DataItemDisplayType.DICTIONARY, displayKey = "DataItemDisplayType")
    private DataItemDisplayType type;

    /**
     * 风格的关键值（如果是querylist形的，就配置key,如果是sqllist形的就配置sqllist,如果是list形的，就直接配置list列表内容)
     */
    @DataItemEntity(value = "DataItemDisplayType", name = "风格的关键值")
    private String displayKey;

    /**
     * 校证输入值类型
     */
    @DataItemEntity(value = "DataItemValueType", name = "校证输入值类型", type = DataItemDisplayType.DICTIONARY, displayKey = "DataItemValueType")
    private DataItemValueType valueType;

    /**
     * 长度
     */
    @DataItemEntity(value = "maxlength", name = "长度")
    private int maxlength;

    /**
     * 精度
     */
    @DataItemEntity(value = "digits", name = "精度")
    private int digits;

    public DataItem() {
        super();
    }

    public DataItem(String code, String name, String description, DataItemDisplayType type, String displayKey, DataItemValueType valueType, int maxlength,
                    int digits) {
        super(code, name, description);
        this.type = type;
        this.displayKey = displayKey;
        this.valueType = valueType;
        this.maxlength = maxlength;
        this.digits = digits;
    }

    public DataItemDisplayType getType() {
        return type;
    }

    public void setType(DataItemDisplayType type) {
        this.type = type;
    }

    public DataItemValueType getValueType() {
        return valueType;
    }

    public void setValueType(DataItemValueType valueType) {
        this.valueType = valueType;
    }

    public String getDisplayKey() {
        return displayKey;
    }

    public void setDisplayKey(String displayKey) {
        this.displayKey = displayKey;
    }


    public int getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    boolean isExtend() {
        return false;
    }
}
