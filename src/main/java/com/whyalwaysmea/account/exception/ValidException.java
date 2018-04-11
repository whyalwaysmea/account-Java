package com.whyalwaysmea.account.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Long
 * @Date: Create in 15:47 2018/4/11
 * @Description:    参数非法异常
 */
@Getter
@Setter
public class ValidException extends RuntimeException {

    private String errorMessage;

    public ValidException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
