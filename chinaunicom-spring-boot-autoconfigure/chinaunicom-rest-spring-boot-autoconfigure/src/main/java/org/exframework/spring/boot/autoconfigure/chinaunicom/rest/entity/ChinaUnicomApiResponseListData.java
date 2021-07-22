package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity;

import java.util.List;

/**
 * 产互列表数据
 *
 * @author rwe
 * @date 2021/7/21 16:19
 **/
public class ChinaUnicomApiResponseListData<T> {


    private List<T> records;

    public ChinaUnicomApiResponseListData() {
    }

    public ChinaUnicomApiResponseListData(List<T> records) {
        this.records = records;
    }

    public List<T> getRecords() {
        return records;
    }

    public ChinaUnicomApiResponseListData setRecords(List<T> records) {
        this.records = records;
        return this;
    }
}
