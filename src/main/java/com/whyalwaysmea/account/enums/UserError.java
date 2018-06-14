package com.whyalwaysmea.account.enums;

import lombok.Getter;

/**
 * @Author: Long
 * @Date: Create in 14:29 2018/6/14
 * @Description:
 */
@Getter
public enum UserError implements CodeEnum {
    ERROR_USERID(3000, "用户id有误");

    private Integer code;
    private String message;

    UserError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}