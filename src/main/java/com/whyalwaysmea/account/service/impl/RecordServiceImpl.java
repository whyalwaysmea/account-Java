package com.whyalwaysmea.account.service.impl;

import com.google.common.collect.Lists;
import com.whyalwaysmea.account.constant.Constant;
import com.whyalwaysmea.account.enums.WaysError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.AccountRecordMapper;
import com.whyalwaysmea.account.mapper.AccountRecordPartersMapper;
import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.*;
import com.whyalwaysmea.account.service.*;
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

    @Autowired
    private AccountBookService accountBookService;

    @Autowired
    private ExpenditureService expenditureService;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private WaysService waysService;

    @Override
    public AccountRecord addRecord(RecordParam recordParam) {
        String userId = getCurrentUserId();
        // 账本统计更新（最后记账时间，预算，）
        accountBookService.updateAccountRecord(recordParam);

        // 新增记录
        AccountRecord accountRecord = new AccountRecord();
        BeanUtils.copyProperties(recordParam, accountRecord);
        accountRecord.setCreatorId(userId);
        if(Constant.INCOME.equals(recordParam.getRecordType())) {
            IncomeType mainType = incomeService.getIncomeType(recordParam.getMainType());
            accountRecord.setMainType(mainType.getName());
            if(recordParam.getSecondaryType() != null) {
                IncomeType secondaryType = incomeService.getIncomeType(recordParam.getSecondaryType());
                if(!secondaryType.getPid().equals(mainType.getId())) {
                    throw new MyException(WaysError.ERROR_ID);
                }
                accountRecord.setSecondaryType(secondaryType.getName());
            }

        } else {
            ExpenditureType mainType = expenditureService.findExpenditureById(recordParam.getMainType());
            accountRecord.setMainType(mainType.getName());
            if(recordParam.getSecondaryType() != null) {
                ExpenditureType secondartType = expenditureService.findExpenditureById(recordParam.getSecondaryType());
                if(!secondartType.getPid().equals(mainType.getId())) {
                    throw new MyException(WaysError.ERROR_ID);
                }
                accountRecord.setSecondaryType(secondartType.getName());
            }
        }
        PayIncomeWays payIncomeWays = waysService.getPayIncomeWays(recordParam.getPayIncomeWay());
        accountRecord.setPayIncomeWay(payIncomeWays.getName());
        accountRecordMapper.insertSelective(accountRecord);

        // 关联的消费者
        List<String> partersId = recordParam.getPartersId();
        if(CollectionUtils.isEmpty(partersId)) {
            partersId = Lists.newArrayList(userId);
        }
        List<AccountRecordParters> parters = partersId.stream().map(id -> {
            AccountRecordParters accountRecordParters = new AccountRecordParters();
            accountRecordParters.setRecordId(accountRecord.getId());
            accountRecordParters.setWechatOpenid(id);
            return accountRecordParters;
        }).collect(Collectors.toList());
        recordPartersMapper.insertList(parters);


        // 用户统计更新（最后记账时间）
        userService.updateLastAccountTime(userId);
        return accountRecord;
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
