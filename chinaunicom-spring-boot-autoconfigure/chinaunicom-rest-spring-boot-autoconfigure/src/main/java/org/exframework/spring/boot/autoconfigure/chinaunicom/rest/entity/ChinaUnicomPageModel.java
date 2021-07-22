package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity;

import org.exframework.support.common.entity.PageModel;
import org.exframework.support.common.entity.Pager;

import java.util.List;

/**
 * 产互分页模型
 *
 * @author rwe
 * @date 2021/7/15 16:59
 **/
public class ChinaUnicomPageModel<T> extends ChinaUnicomPager {

    /**
     * 列表数据
     */
    private List<T> records;

    public ChinaUnicomPageModel(Pager pager, List<T> list) {
       this(new ChinaUnicomPager(Long.valueOf(list.size()), pager), list);
    }

    public ChinaUnicomPageModel(ChinaUnicomPager pager, List<T> list) {
        super(pager.getTotal(), new ChinaUnicomPage(pager.getCurrent(), pager.getSize()));
        this.records = list;
    }

    public ChinaUnicomPageModel(PageModel<T> pageModel) {
        this(pageModel.getPager(), pageModel.getList());
    }

    public List<T> getRecords() {
        return records;
    }

    public ChinaUnicomPageModel<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }
}
