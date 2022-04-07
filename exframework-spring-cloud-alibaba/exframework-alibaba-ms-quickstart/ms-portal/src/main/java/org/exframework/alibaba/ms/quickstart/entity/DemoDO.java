package org.exframework.alibaba.ms.quickstart.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.exframework.support.rest.annotation.IdCardValue;

/**
 * 数据实体类
 * @author pro
 *
 */
@ApiModel("demo")
public class DemoDO {

    @ApiModelProperty("身份证")
    @IdCardValue
    private String idCard;

    public String getIdCard() {
        return idCard;
    }

    public DemoDO setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }
}
