package org.exframework.support.rest.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.swagger.annotations.ApiModelProperty;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author rwe
 * @since Nov 29, 2020
 * <p>
 * Copyright @ 2020
 * 接口全局处理
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 10)
public class GlobalControllerExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionControllerAdvice.class.getName());

    public static final String PARAMS_ERROR = "参数校验错误";

    /**
     * 参数范围校验错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseData<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        errorHandle(ex);
        return bindingResultHandler(ex.getBindingResult());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ApiResponseData<List<String>> webExchangeBindExceptionHandler(WebExchangeBindException ex) {
        errorHandle(ex);
        return bindingResultHandler(ex.getBindingResult());
    }

    private ApiResponseData<List<String>> bindingResultHandler(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();
        if (bindingResult.hasGlobalErrors()) {
            errors.addAll(bindingResult.getGlobalErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        if (bindingResult.hasFieldErrors()) {
            errors.addAll(bindingResult.getFieldErrors().stream().map(fieldError -> {
                Field field = ReflectionUtils.findField(Objects.requireNonNull(bindingResult.getTarget()).getClass(), fieldError.getField());
                if (Objects.requireNonNull(field).isAnnotationPresent(ApiModelProperty.class)) {
                    ApiModelProperty property = field.getAnnotation(ApiModelProperty.class);
                    return MessageFormat.format("{0}[{1}]{2}", StringUtils.hasText(property.name()) ? property.name() : property.value(), fieldError.getField(), fieldError.getDefaultMessage());
                } else {
                    return MessageFormat.format("{0}{1}", fieldError.getField(), fieldError.getDefaultMessage());
                }
            }).collect(Collectors.toList()));
        }
        return new ApiResponseData<>(ResultCode.BAD_REQUEST, PARAMS_ERROR, errors);
    }

    @ExceptionHandler(CheckerException.class)
    public ApiResponseData<Object> apiExceptionHandler(CheckerException e) {
        errorHandle(e);
        return new ApiResponseData<>(e.getCode(), e.getMessage(), false, e.getData());
    }

    @ExceptionHandler(ApiException.class)
    public ApiResponseData<Object> apiExceptionHandler(ApiException e) {
        errorHandle(e);
        return new ApiResponseData<>(e.getCode(), e.getMessage(), false, e.getData());
    }

    @ExceptionHandler(ServerException.class)
    public ApiResponseData<Object> serverExceptionHandler(ServerException e) {
        errorHandle(e);
        return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponseData<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        errorHandle(e);
        return new ApiResponseData<>(ResultCode.BAD_REQUEST, "请求参数错误,请检查", e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ApiResponseData<Object> nullPointerExceptionHandler(NullPointerException e) {
        errorHandle(e);
        return new ApiResponseData<>(ResultCode.SERVICE_UNAVAILABLE, "空指针错误,请联系管理员", e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ApiResponseData<Object> nullPointerExceptionHandler(IOException e) {
        errorHandle(e);
        return new ApiResponseData<>(ResultCode.SERVICE_UNAVAILABLE, "文件错误,请联系管理员", e.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ApiResponseData<Object> exceptionHandler(InvalidFormatException e) {
        errorHandle(e);
        return new ApiResponseData<>(ResultCode.NOT_ACCEPTABLE, "格式错误:" + e.getMessage(), null);
    }


    @ExceptionHandler(Exception.class)
    public ApiResponseData<Object> exceptionHandler(Exception e) {
        errorHandle(e);
        return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage(), null);
    }

    private void errorHandle(Exception e) {
        logger.error(MessageFormat.format("全局错误处理:{0}", e.getMessage()), e);
    }
}
