package org.exframework.spring.boot.chinaunicom.rest.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exframework.support.rest.enums.ResultCode;

public class ChinaUnicomApiResponseData<T> extends ChinaUnicomApiResponse {

    @SuppressWarnings("rawtypes")
    public static final ChinaUnicomApiResponseData NOT_ENOUGH = new ChinaUnicomApiResponseData<>(ResultCode.BAD_REQUEST, PARAM_NOT_ENOUGH, null);

    @SuppressWarnings("rawtypes")
    public static final ChinaUnicomApiResponseData PARAM_ERROR = new ChinaUnicomApiResponseData<>(ResultCode.BAD_REQUEST, PARAMS_ERROR, null);

    @SuppressWarnings("rawtypes")
    public static final ChinaUnicomApiResponseData EMPTY = new ChinaUnicomApiResponseData<>(null);

    /**
     * 数据信息
     */
    private T data;

    public ChinaUnicomApiResponseData() {
        this(ResultCode.INTERNAL_SERVER_ERROR, null);
    }

    public ChinaUnicomApiResponseData(T data) {
        this(ResultCode.OK, data);
    }

    public ChinaUnicomApiResponseData(ResultCode resultCode, T data) {
        this(resultCode, resultCode.getMessage(), data);
    }

    public ChinaUnicomApiResponseData(ResultCode resultCode, String message, T data) {
        this(String.valueOf(resultCode.getCode()), message, data);
    }

    public ChinaUnicomApiResponseData(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    public static <E> ChinaUnicomApiResponseData<E> notEnough() {
        return (ChinaUnicomApiResponseData<E>) NOT_ENOUGH;
    }

    @SuppressWarnings("unchecked")
    public static final <E> ChinaUnicomApiResponseData<E> paramError() {
        return (ChinaUnicomApiResponseData<E>) PARAM_ERROR;
    }

    public static final <E> ChinaUnicomApiResponseData<E> notFound(String message) {
        return new ChinaUnicomApiResponseData<E>(ResultCode.NOT_FOUND, message, null);
    }

    @SuppressWarnings("unchecked")
    public static final <E> ChinaUnicomApiResponseData<E> empltyData() {
        return (ChinaUnicomApiResponseData<E>) EMPTY;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "ChinaUnicomApiResponseData{" +
                    "data=" + data +
                    '}';
        }
    }
}
