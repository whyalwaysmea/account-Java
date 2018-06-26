package com.whyalwaysmea.account.mq;

import lombok.Getter;

/**
 * @Author: Long
 * @Date: Create in 17:52 2018/6/25
 * @Description:  队列信息
 */
@Getter
public enum QueueEnum {
    /**
     * 用户注册
     */
    USER_REGISTER("user.register"),

    /**
     * 用户登录
     */
    USER_LOGIN("user.login")
    ;

    /**
     * 队列名称
     */
    private String name;


    QueueEnum(String name) {
        this.name = name;
    }

}
