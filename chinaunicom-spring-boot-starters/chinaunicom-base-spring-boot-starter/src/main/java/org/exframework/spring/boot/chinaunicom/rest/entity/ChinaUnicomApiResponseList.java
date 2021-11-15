package org.exframework.spring.boot.chinaunicom.rest.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "ChinaUnicomApiResponseList{" +
                    "data=" + data +
                    '}';
        }
    }
}
