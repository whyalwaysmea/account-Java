package com.whyalwaysmea.account.exception;

import com.whyalwaysmea.account.enums.CodeEnum;
import lombok.Data;

/**
 * @Author: Long
 * @Date: Create in 15:27 2018/4/11
 * @Description:    自定义异常
 */
@Data
public class MyException extends RuntimeException {

    private int code;

    public MyException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }
}
