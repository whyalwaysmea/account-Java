package com.whyalwaysmea.account.enums;

import lombok.Getter;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/14 11:16
 * @Description:
 */
@Getter
public enum AccountBookError implements CodeEnum {
    ERROR_INSERT(2000, "insert error");

    private Integer code;
    private String message;

    AccountBookError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}