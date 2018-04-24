package com.whyalwaysmea.account.enums;

import lombok.Getter;

/**
 * @Author: Long
 * @Date: Create in 15:32 2018/4/11
 * @Description:
 */
@Getter
public enum WaysError implements CodeEnum {
    ERROR_ID(1000, "错误的条目id"),
    ERROR_PID(1001, "错误的条目pid");

    private Integer code;
    private String message;

    WaysError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
