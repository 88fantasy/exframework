package com.gzmpc.support.sso.core.exception;

import com.gzmpc.support.sso.core.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-06-06 16:58
 */
@Slf4j
@ControllerAdvice
public class LoinUserExceptionHandler {

    private final static String EXCEPTION_MSG_KEY = "Exception message : ";

    @ResponseBody
    @ExceptionHandler(LoginUserException.class)
    public ResponseResult handleValidException(LoginUserException e){
        ResponseResult result = new ResponseResult();
        //日志记录错误信息
        log.error(e.getMessage());
        //将错误信息返回给前台
        result.setCode(HttpStatus.SC_UNAUTHORIZED);
        result.setMsg(e.getMessage());
        return result;
    }
}

