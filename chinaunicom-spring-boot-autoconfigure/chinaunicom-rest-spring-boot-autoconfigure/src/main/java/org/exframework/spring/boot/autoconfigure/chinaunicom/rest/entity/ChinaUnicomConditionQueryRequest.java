package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.Collection;

/**
 * 产互通用查询请求
 *
 * @author rwe
 * @date 2021/7/16 09:25
 **/
@ApiModel("产互通用查询请求")
public class ChinaUnicomConditionQueryRequest extends ChinaUnicomRequestPage {

    private static Collection<String> KEYWORDS = Arrays.asList( "current", "pageNo", "pageSize", "sorts");

    @ApiModelProperty("排序信息")
    private String[] sorts;

    public String[] getSorts() {
        return sorts;
    }

    public ChinaUnicomConditionQueryRequest setSorts(String[] sorts) {
        this.sorts = sorts;
        return this;
    }

    public Collection<String> ingoreList() {
        return KEYWORDS;
    }
}
