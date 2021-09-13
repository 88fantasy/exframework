package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity;

import org.exframework.support.rest.entity.ApiResponse;
import org.exframework.support.rest.enums.ResultCode;

import java.util.List;

/**
 * 产互列表返回
 *
 * @author rwe
 * @date 2021/7/15 18:53
 **/
public class ChinaUnicomApiResponseList<T> extends ChinaUnicomApiResponse {

    /**
     * 列表数据
     */
    private ChinaUnicomApiResponseListData<T> data;

    public ChinaUnicomApiResponseList(List<T> records) {
        this(ResultCode.OK, records);
    }

    public ChinaUnicomApiResponseList(ResultCode resultCode, List<T> records) {
        this(resultCode, resultCode.getMessage(), records);
    }

    public ChinaUnicomApiResponseList(ResultCode resultCode, String message, List<T> records) {
        this(String.valueOf(resultCode.getCode()), message, records);
    }

    public ChinaUnicomApiResponseList(String code, String message, List<T> records) {
        super(code, message);
        this.data = new ChinaUnicomApiResponseListData<>(records);
    }

    public ChinaUnicomApiResponseListData<T> getData() {
        return data;
    }

    public ChinaUnicomApiResponseList<T> setData(ChinaUnicomApiResponseListData<T> data) {
        this.data = data;
        return this;
    }
}
