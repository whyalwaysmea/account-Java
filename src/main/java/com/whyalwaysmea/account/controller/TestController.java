package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.mq.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Long
 * @Date: Create in 15:16 2018/6/26
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public void test(@RequestParam("openId") String openId) {
        rabbitTemplate.convertAndSend(QueueEnum.USER_REGISTER.getName(), openId);
    }
}
