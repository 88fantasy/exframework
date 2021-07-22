package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity;

import org.exframework.support.common.entity.PageModel;
import org.exframework.support.rest.entity.ApiResponse;
import org.exframework.support.rest.entity.ApiResponsePage;
import org.exframework.support.rest.enums.ResultCode;
import org.exframework.support.rest.exception.GlobalControllerExceptionControllerAdvice;

/**
 * 产互分页返回
 *
 * @author rwe
 * @date 2021/7/15 16:42
 **/
public class ChinaUnicomApiResponsePage<T> extends ApiResponse {

    @SuppressWarnings("rawtypes")
    public static final ChinaUnicomApiResponsePage NOT_ENOUGH = new ChinaUnicomApiResponsePage<>(ResultCode.BAD_REQUEST, PARAM_NOT_ENOUGH, null);

    @SuppressWarnings("rawtypes")
    public static final ChinaUnicomApiResponsePage PARAM_ERROR = new ChinaUnicomApiResponsePage<>(ResultCode.BAD_REQUEST, GlobalControllerExceptionControllerAdvice.PARAMS_ERROR, null);

    @SuppressWarnings("rawtypes")
    public static final ChinaUnicomApiResponsePage EMPTY = new ChinaUnicomApiResponsePage<>(new ChinaUnicomPageModel<>(PageModel.EMPTY));

    /**
     * 分页数据
     */
    private ChinaUnicomPageModel<T> data;


    public ChinaUnicomApiResponsePage(ApiResponsePage<T> page) {
        this(page.getCode(), page.getMessage(), page.isStatus(), new ChinaUnicomPageModel<T>(page.getData()));
    }

    public ChinaUnicomApiResponsePage(ChinaUnicomPageModel<T> data) {
        this(ResultCode.OK, data);
    }

    public ChinaUnicomApiResponsePage(ResultCode resultCode, ChinaUnicomPageModel<T> data) {
        this(resultCode, resultCode.getMessage(), data);
    }

    public ChinaUnicomApiResponsePage(ResultCode resultCode, String message, ChinaUnicomPageModel<T> data) {
        this(resultCode.getCode(), message, !(resultCode.getCode() >= 400 && resultCode.getCode() <= 599), data);
    }

    public ChinaUnicomApiResponsePage(int code, String message, boolean status, ChinaUnicomPageModel<T> data) {
        super(code, message, status);
        this.data = data;
    }

    public ChinaUnicomPageModel<T> getData() {
        return data;
    }

    public ChinaUnicomApiResponsePage<T> setData(ChinaUnicomPageModel<T> data) {
        this.data = data;
        return this;
    }

    @SuppressWarnings("unchecked")
    public static <E> ChinaUnicomApiResponsePage<E> notEnough() {
        return (ChinaUnicomApiResponsePage<E>)NOT_ENOUGH;
    }

    @SuppressWarnings("unchecked")
    public static final <E> ChinaUnicomApiResponsePage<E> paramError() {
        return (ChinaUnicomApiResponsePage<E>)PARAM_ERROR;
    }

    public static final <E> ChinaUnicomApiResponsePage<E> notFound(String message) {
        return new ChinaUnicomApiResponsePage<E>(ResultCode.NOT_FOUND, message, null);
    }

    public static final <E> ChinaUnicomApiResponsePage<E> empltyPage() {
        return (ChinaUnicomApiResponsePage<E>)EMPTY;
    }
}
