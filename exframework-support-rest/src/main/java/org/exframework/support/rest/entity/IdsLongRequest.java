package org.exframework.support.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 数字主键请求
 *
 * @author rwe
 * @date 2021/10/11 23:53
 **/
@ApiModel("数字主键请求")
public class IdsLongRequest {

    @NotEmpty
    @ApiModelProperty("主键数组")
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public IdsLongRequest setIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }
}
