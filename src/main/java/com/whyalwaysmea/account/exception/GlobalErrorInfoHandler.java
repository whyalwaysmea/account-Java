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


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExecuteResult globalException(HttpServletRequest request, Throwable ex) {
        log.info(ex.getMessage(), ex);
        return ExecuteResult.fail(500, ex.getMessage());
    }
}
