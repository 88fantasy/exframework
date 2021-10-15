package org.exframework.support.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 字符串主键请求
 *
 * @author rwe
 * @date 2021/10/11 23:53
 **/
@ApiModel("字符串主键请求")
public class IdsStringRequest {

    @NotEmpty
    @ApiModelProperty("主键数组")
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public IdsStringRequest setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }
}
