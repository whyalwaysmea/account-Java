package com.whyalwaysmea.account.mq;

import com.whyalwaysmea.account.dto.SyncAccountBookDTO;
import com.whyalwaysmea.account.mapper.AccountRecordPartersMapper;
import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountRecordParters;
import com.whyalwaysmea.account.service.AccountBookService;
import com.whyalwaysmea.account.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Long
 * @Date: Create in 17:06 2018/6/29
 * @Description:    处理同步账本记录消息
 */
@Component
@RabbitListener(queues = "record.sync")
public class SyncAccountBookMessage {

    @Autowired
    private AccountRecordPartersMapper recordPartersMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountBookService accountBookService;

    @RabbitHandler
    public void updateRecordInfo(SyncAccountBookDTO syncAccountBookDTO) {
        RecordParam recordParam = syncAccountBookDTO.getRecordParam();
        // 关联的消费者
        List<String> partersId = recordParam.getPartersId();
        List<AccountRecordParters> parters = partersId.stream().map(id -> {
            AccountRecordParters accountRecordParters = new AccountRecordParters();
            accountRecordParters.setRecordId(recordParam.getId());
            accountRecordParters.setWechatOpenid(id);
            return accountRecordParters;
        }).collect(Collectors.toList());
        recordPartersMapper.insertList(parters);


        // 用户统计更新（最后记账时间）
        userService.updateLastAccountTime(syncAccountBookDTO.getUserId());

        // 账本统计更新（最后记账时间，预算，）
        accountBookService.updateAccountRecord(recordParam);
    }

}
