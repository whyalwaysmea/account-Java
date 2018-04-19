package com.whyalwaysmea.account.service.impl;

import com.google.common.collect.Lists;
import com.whyalwaysmea.account.mapper.AccountRecordMapper;
import com.whyalwaysmea.account.mapper.AccountRecordPartersMapper;
import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountRecord;
import com.whyalwaysmea.account.po.AccountRecordParters;
import com.whyalwaysmea.account.service.RecordService;
import com.whyalwaysmea.account.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/18 20:48
 * @Description:
 */
@Service
public class RecordServiceImpl extends BaseService implements RecordService {

    @Autowired
    private AccountRecordMapper accountRecordMapper;

    @Autowired
    private AccountRecordPartersMapper recordPartersMapper;

    @Autowired
    private UserService userService;


    @Override
    public AccountRecord addRecord(RecordParam recordParam) {
        String userId = getCurrentUserId();
        // 账本统计更新（最后记账时间，预算，）


        // 新增记录
        AccountRecord accountRecord = new AccountRecord();
        BeanUtils.copyProperties(recordParam, accountRecord);
        accountRecord.setCreatorId(userId);
        accountRecordMapper.insertSelective(accountRecord);

        // 关联的消费者
        List<String> partersId = recordParam.getPartersId();
        if(CollectionUtils.isEmpty(partersId)) {
            partersId = Lists.newArrayList(userId);
        }
        List<AccountRecordParters> parters = partersId.stream().map(id -> {
            AccountRecordParters accountRecordParters = new AccountRecordParters();
            accountRecordParters.setRecordId(recordParam.getBookId());
            accountRecordParters.setWechatOpenid(id);
            return accountRecordParters;
        }).collect(Collectors.toList());
        recordPartersMapper.insertList(parters);


        // 用户统计更新（最后记账时间）
        userService.updateLastAccountTime(userId);
        return null;
    }

    @Override
    public AccountRecord updateRecord(RecordParam recordParam) {
        return null;
    }

    @Override
    public boolean delRecord(long id) {
        return false;
    }
}
