package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.constant.RecordType;
import com.whyalwaysmea.account.dto.RecordListItem;
import com.whyalwaysmea.account.dto.SyncAccountBookDTO;
import com.whyalwaysmea.account.enums.WaysError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.AccountBookPartersMapper;
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
import com.whyalwaysmea.account.utils.DateUtils;
import com.whyalwaysmea.account.utils.UserUtils;
import com.whyalwaysmea.account.vo.RecordListVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private AccountBookPartersMapper partersMapper;

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

    @Override
    public List<RecordListVO> getRecords(long bookId, String date) {
        List<String> parterIds = partersMapper.getParterIds(bookId);
        if(!parterIds.contains(UserUtils.getCurrentUserId())) {
            return null;
        }
        List<RecordListItem> recordList = accountRecordMapper.getRecordList(bookId, date);
        if(CollectionUtils.isEmpty(recordList)) {
            return null;
        }
        List<RecordListItem> items = new ArrayList<>();
        Date currentDate = recordList.get(0).getRecordTime();
        long inputAmount = 0;
        long expenditureAmount = 0;
        List<RecordListVO> result = new ArrayList<>();
        RecordListVO recordListVO = null;
        for (int i = 0; i < recordList.size(); i++) {
            RecordListItem item = recordList.get(i);

            if(item.getRecordTime().compareTo(currentDate) != 0) {
                recordListVO = new RecordListVO();
                recordListVO.setIncomeAmount(inputAmount);
                recordListVO.setExpenditureAmount(expenditureAmount);
                recordListVO.setDate(currentDate);
                recordListVO.setWeek(DateUtils.date2WeekStr(currentDate));
                recordListVO.setItems(items);
                result.add(recordListVO);

                inputAmount = 0;
                expenditureAmount = 0;
                currentDate = item.getRecordTime();
                items = new ArrayList<>();
            }

            if(RecordType.INCOME.equals(item.getRecordType())) {
                inputAmount += item.getAmount();
            } else {
                expenditureAmount += item.getAmount();
            }
            items.add(item);

            if(i == recordList.size() - 1) {
                recordListVO = new RecordListVO();
                recordListVO.setIncomeAmount(inputAmount);
                recordListVO.setExpenditureAmount(expenditureAmount);
                recordListVO.setDate(currentDate);
                recordListVO.setWeek(DateUtils.date2WeekStr(currentDate));
                recordListVO.setItems(items);
                result.add(recordListVO);
            }
        }
        return result;
    }
}
