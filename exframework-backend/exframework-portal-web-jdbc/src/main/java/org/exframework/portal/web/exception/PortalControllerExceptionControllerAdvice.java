package org.exframework.portal.web.exception;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.DataTruncation;
import java.sql.SQLException;

/**
 * 界面统一错误处理
 *
 * @author rwe
 * @date 2021/7/30 15:47
 **/
@RestControllerAdvice
public class PortalControllerExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(PortalControllerExceptionControllerAdvice.class.getName());

    public static final String DATABASE_ERROR = "数据库执行错误";

    /**
     * 数据库执行错误错误
     */
    @ExceptionHandler(PersistenceException.class)
    public ApiResponseData<?> persistenceExceptionHandler(PersistenceException e) {
        logger.error(e.getMessage(), e);
        return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, DATABASE_ERROR, e.getMessage());
    }

    /**
     * 数据库执行错误错误
     */
    @ExceptionHandler(DataTruncation.class)
    public ApiResponseData<?> dataTruncationHandler(DataTruncation e) {
        logger.error(e.getMessage(), e);
        return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, DATABASE_ERROR, e.getMessage());
    }

    /**
     * 数据库执行错误错误
     */
    @ExceptionHandler(SQLException.class)
    public ApiResponseData<?> sqlExceptionHandler(SQLException e) {
        logger.error(e.getMessage(), e);
        return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, DATABASE_ERROR, e.getMessage());
    }

    @ExceptionHandler(TooManyResultsException.class)
    public ApiResponseData<?> tooManyResultsExceptionHandler(TooManyResultsException e) {
        logger.error(e.getMessage(), e);
        return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, DATABASE_ERROR, "记录数过多");
    }
    /**
     * 数据库执行错误错误
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public ApiResponseData<?> myBatisSystemExceptionHandler(MyBatisSystemException e) {
        logger.error(e.getMessage(), e);
        return new ApiResponseData<>(ResultCode.INTERNAL_SERVER_ERROR, DATABASE_ERROR, e.getMessage());
    }
}
