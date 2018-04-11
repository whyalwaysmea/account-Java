package com.whyalwaysmea.account.enums;

import lombok.Getter;

/**
 * @Author: Long
 * @Date: Create in 15:32 2018/4/11
 * @Description:
 */
@Getter
public enum WaysError implements CodeEnum {
    ERROR_Expenditure_ID(1000, "error id"),
    ERROR_Expenditure_PID(1001, "error pid");

    private Integer code;
    private String message;

    WaysError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
