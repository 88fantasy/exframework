package org.exframework.spring.boot.chinaunicom.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.exframework.support.common.entity.Page;
import org.springframework.lang.Nullable;

/**
 * 产互分页请求
 *
 * @author rwe
 * @date 2021/7/16 09:17
 **/
@ApiModel("产互分页请求")
public class ChinaUnicomRequestPage {

    /**
     * 当前页
     */
    @Nullable
    @ApiModelProperty("当前页")
    private Long pageNo;

    /**
     * 每页条数
     */
    @Nullable
    @ApiModelProperty("每页条数")
    private Long pageSize;

    public ChinaUnicomRequestPage() {
        this.pageNo = DEFAULT.pageNo;
        this.pageSize = DEFAULT.pageSize;
    }

    public ChinaUnicomRequestPage(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public ChinaUnicomRequestPage(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo.longValue();
        this.pageSize = pageSize.longValue();
    }

    @Nullable
    public Long getCurrent() {
        return pageNo;
    }

    public ChinaUnicomRequestPage setCurrent(@Nullable Long pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    @Nullable
    public Long getPageSize() {
        return pageSize;
    }

    public ChinaUnicomRequestPage setPageSize(@Nullable Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public static ChinaUnicomRequestPage DEFAULT = new ChinaUnicomRequestPage(1L,20L);

    public Page toPage() {
        return new Page(pageNo, pageSize);
    }
}
