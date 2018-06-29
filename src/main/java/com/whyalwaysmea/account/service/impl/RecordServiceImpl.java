package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.constant.RecordType;
import com.whyalwaysmea.account.dto.SyncAccountBookDTO;
import com.whyalwaysmea.account.enums.WaysError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.AccountRecordMapper;
import com.whyalwaysmea.account.mq.QueueEnum;
import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountRecord;
import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.po.IncomeType;
import com.whyalwaysmea.account.po.PayIncomeWays;
import com.whyalwaysmea.account.service.ExpenditureService;
import com.whyalwaysmea.account.service.IncomeService;
import com.whyalwaysmea.account.service.RecordService;
import com.whyalwaysmea.account.service.WaysService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ExpenditureService expenditureService;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private WaysService waysService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    @Transactional
    public AccountRecord addRecord(RecordParam recordParam) {
        String userId = getCurrentUserId();

        // 新增记录
        AccountRecord accountRecord = new AccountRecord();
        BeanUtils.copyProperties(recordParam, accountRecord);
        accountRecord.setCreatorId(userId);
        if(RecordType.INCOME.equals(recordParam.getRecordType())) {
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


        // 同步账本统计数据  1. 关联消费者 2.用户统计更新 3.账本统计更新
        recordParam.setId(accountRecord.getId());
        SyncAccountBookDTO syncAccountBookDTO = new SyncAccountBookDTO();
        syncAccountBookDTO.setRecordParam(recordParam);
        syncAccountBookDTO.setUserId(userId);
        amqpTemplate.convertAndSend(QueueEnum.RECORD.getName(), syncAccountBookDTO);

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
