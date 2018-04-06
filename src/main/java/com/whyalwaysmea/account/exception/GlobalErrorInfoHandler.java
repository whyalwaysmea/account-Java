package com.whyalwaysmea.account.exception;

import com.whyalwaysmea.account.dto.ExecuteResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
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


    @ExceptionHandler(ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExecuteResult handleShiro(HttpServletRequest request, Throwable ex) {
        log.info(ex.getMessage());
        return ExecuteResult.fail(401, ex.getMessage());

    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ExecuteResult handle401() {
        return ExecuteResult.fail(401, "Unauthorized");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExecuteResult globalException(HttpServletRequest request, Throwable ex) {
        log.info(ex.getMessage(), ex);
        return ExecuteResult.fail(500, ex.getMessage());
    }
}
