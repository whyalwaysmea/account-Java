package com.whyalwaysmea.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:16
 * @Description:
 */
@ApiModel("返回数据结构体")
@Data
public class ExecuteResult<T> implements Serializable {

    /**
     * 执行成功
     */
    @ApiModelProperty("执行是否成功")
    private boolean success;

    @ApiModelProperty("具体的数据格式")
    private T value;

    /**
     * 错误码
     */
    @ApiModelProperty("错误码， 0表示正确")
    private int errorCode;

    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String message;

    private ExecuteResult() {

    }

    public static <T> ExecuteResult<T> ok() {
        ExecuteResult<T> result = new ExecuteResult<>();
        result.errorCode = 0;
        result.success = true;
        return result;
    }

    public static <T> ExecuteResult<T> ok(T value) {
        ExecuteResult<T> result = new ExecuteResult<T>();
        result.errorCode = 0;
        result.success = true;
        result.value = value;
        return result;
    }

    public static <T> ExecuteResult<T> fail() {
        ExecuteResult<T> result = new ExecuteResult<>();
        result.errorCode = -1;
        result.success = false;
        return result;
    }

    public static <T> ExecuteResult<T> fail(String message) {
        ExecuteResult<T> result = new ExecuteResult<>();
        result.errorCode = -1;
        result.success = false;
        result.message = message;
        return result;
    }

    public static <T> ExecuteResult<T> fail(int errorCode) {
        ExecuteResult<T> result = new ExecuteResult<>();
        result.errorCode = errorCode;
        result.success = false;
        return result;
    }

    public static <T> ExecuteResult<T> fail(int errorCode, String message) {
        ExecuteResult<T> result = new ExecuteResult<>();
        result.errorCode = errorCode;
        result.success = false;
        result.message = message;
        return result;
    }

}
