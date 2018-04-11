package com.whyalwaysmea.account.exception;

import com.whyalwaysmea.account.dto.ExecuteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:18
 * @Description: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalErrorInfoHandler {

    /**
     * token异常
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ExecuteResult unauthorizedException(HttpServletRequest request, Throwable ex) {
        log.info(ex.getMessage(), ex);
        return ExecuteResult.fail(401, "Unauthorized");
    }

    /**
     * 自定义异常，通常是逻辑、参数错误
     */
    @ExceptionHandler(MyException.class)
    public ExecuteResult myException(HttpServletRequest request, MyException ex) {
        log.info(ex.getMessage(), ex);
        return ExecuteResult.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler(ValidException.class)
    public ExecuteResult validException(HttpServletRequest request, ValidException ex) {
        log.info(ex.getMessage(), ex);
        return ExecuteResult.fail(900, ex.getMessage());
    }

    /**
     * 服务器内部错误
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExecuteResult globalException(HttpServletRequest request, Throwable ex) {
        log.info(ex.getMessage(), ex);
        return ExecuteResult.fail(500, ex.getMessage());
    }
}
