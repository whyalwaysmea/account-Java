package com.whyalwaysmea.account.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Long
 * @Date: Create in 17:38 2018/6/25
 * @Description:
 */
@Configuration
public class QueueConfiguration {

    /**
     * 配置用户注册队列
     * 并设置持久化队列
     * @return
     */
    @Bean
    public Queue userRegisterQueue() {
        return new Queue(QueueEnum.USER_REGISTER.getName(), true);
    }


    /**
     * 账本同步
     * @return
     */
    @Bean
    public Queue syncAccountBookInfo() {
        return new Queue(QueueEnum.RECORD.getName(), true);
    }


}