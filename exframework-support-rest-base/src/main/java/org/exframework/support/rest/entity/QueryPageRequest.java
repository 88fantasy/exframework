package org.exframework.support.rest.entity;

import io.swagger.annotations.ApiModelProperty;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.RequestIgnoreList;

import java.util.Arrays;
import java.util.Collection;

/**
 * 通用分页请求
 *
 * @author rwe
 * @date 2022/4/26 17:36
 **/

public class QueryPageRequest implements RequestIgnoreList {

    private static Collection<String> KEYWORDS = Arrays.asList( "current", "pageNo", "pageSize", "sorts");

    @ApiModelProperty(value = "分页信息")
    Page page;

    @ApiModelProperty(value = "排序信息")
    String[] sorts;

    public QueryPageRequest() {
        this.page = Page.DEFAULT;
    }

    public Page getPage() {
        return page;
    }

    public QueryPageRequest setPage(Page page) {
        this.page = page;
        return this;
    }

    public String[] getSorts() {
        return sorts;
    }

    public QueryPageRequest setSorts(String[] sorts) {
        this.sorts = sorts;
        return this;
    }

    public Collection<String> ignoreList() {
        return KEYWORDS;
    }
}
