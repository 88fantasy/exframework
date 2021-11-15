package org.exframework.spring.boot.chinaunicom.rest.entity;

import org.exframework.support.common.entity.Page;

/**
 * 产互分页信息
 *
 * @author rwe
 * @date 2021/7/15 17:22
 **/
public class ChinaUnicomPager extends ChinaUnicomPage {

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 总数
     */
    private Long total;

    public ChinaUnicomPager(Long total, Page page) {
        this.total = total;
        this.setCurrent(page.getCurrent());
        this.setSize(page.getPageSize());
        this.pages = page.getPageSize() != null && page.getPageSize() != 0 ? ((total / page.getPageSize())+1): 0 ;
    }

    public ChinaUnicomPager(Long total, ChinaUnicomPage page) {
        this.total = total;
        this.setCurrent(page.getCurrent());
        this.setSize(page.getSize());
        this.pages = page.getSize() != null && page.getSize() != 0 ? ((total / page.getSize())+1): 0 ;
    }

    public Long getPages() {
        return pages;
    }

    public Long getTotal() {
        return total;
    }

    public ChinaUnicomPager setTotal(Long total) {
        this.total = total;
        return this;
    }
}
