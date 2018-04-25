package com.whyalwaysmea.account.enums;

import lombok.Getter;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/15 11:07
 * @Description: 普通的错误信息
 */
@Getter
public enum CommonError implements CodeEnum {
    INSUFFICIENT_PERMISSIONS(800, "没有权限操作"),
    ERROR_PARAMTER(800, "没有权限操作");

    private Integer code;
    private String message;

    CommonError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
