package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.swagger.annotations.ApiModelProperty;
import org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity.ChinaUnicomApiResponseData;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.exframework.support.rest.exception.ApiException;
import org.exframework.support.rest.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rwe
 * @since Nov 29, 2020
 * <p>
 * Copyright @ 2020
 * 接口全局处理
 */
@Primary
@RestControllerAdvice
public class ChinaUnicomGlobalControllerExceptionControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(ChinaUnicomGlobalControllerExceptionControllerAdvice.class.getName());

    public static final String PARAMS_ERROR = "参数校验错误";

    /**
     * 参数范围校验错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ChinaUnicomApiResponseData<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        List<String> errors = new ArrayList<>();
        if (e.hasGlobalErrors()) {
            errors.addAll(e.getGlobalErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        if (e.hasFieldErrors()) {
            errors.addAll(e.getFieldErrors().stream().map(fieldError -> {
                Field field = ReflectionUtils.findField(e.getBindingResult().getTarget().getClass(), fieldError.getField());
                if (field.isAnnotationPresent(ApiModelProperty.class)) {
                    ApiModelProperty property = field.getAnnotation(ApiModelProperty.class);
                    return MessageFormat.format("{0}[{1}]{2}", StringUtils.hasText(property.name()) ? property.name() : property.value(), fieldError.getField(), fieldError.getDefaultMessage());
                } else {
                    return MessageFormat.format("{0}{1}", fieldError.getField(), fieldError.getDefaultMessage());
                }
            }).collect(Collectors.toList()));
        }
        return new ChinaUnicomApiResponseData<>(ResultCode.BAD_REQUEST, PARAMS_ERROR, errors);
    }

    @ExceptionHandler(ApiException.class)
    public ChinaUnicomApiResponseData<Object> apiExceptionHandler(ApiException e) {
        errorHandle(e);
        return new ChinaUnicomApiResponseData<>(String.valueOf(e.getCode()), e.getMessage(), e.getData());
    }

    @ExceptionHandler(ServerException.class)
    public ChinaUnicomApiResponseData<Object> serverExceptionHandler(ServerException e) {
        errorHandle(e);
        return new ChinaUnicomApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ChinaUnicomApiResponseData<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        errorHandle(e);
        return new ChinaUnicomApiResponseData<>(ResultCode.BAD_REQUEST, "请求参数错误,请检查", e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ChinaUnicomApiResponseData<Object> nullPointerExceptionHandler(NullPointerException e) {
        errorHandle(e);
        return new ChinaUnicomApiResponseData<>(ResultCode.SERVICE_UNAVAILABLE, "空指针错误,请联系管理员", e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ChinaUnicomApiResponseData<Object> nullPointerExceptionHandler(IOException e) {
        errorHandle(e);
        return new ChinaUnicomApiResponseData<>(ResultCode.SERVICE_UNAVAILABLE, "文件错误,请联系管理员", e.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ChinaUnicomApiResponseData<Object> exceptionHandler(InvalidFormatException e) {
        errorHandle(e);
        return new ChinaUnicomApiResponseData<>(ResultCode.NOT_ACCEPTABLE, "格式错误:" + e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ChinaUnicomApiResponseData<Object> exceptionHandler(Exception e) {
        errorHandle(e);
        return new ChinaUnicomApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private void errorHandle(Exception e) {
        logger.error(MessageFormat.format("全局错误处理:{0}", e.getMessage()), e);
    }
}
