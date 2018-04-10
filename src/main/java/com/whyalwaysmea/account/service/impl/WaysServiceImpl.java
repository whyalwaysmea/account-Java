package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.ExpenditureTypeMapper;
import com.whyalwaysmea.account.mapper.IncomeTypeMapper;
import com.whyalwaysmea.account.parameters.IncomeAndExpenditureTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.service.WaysService;
import com.whyalwaysmea.account.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Long
 * @Date: Create in 16:49 2018/4/10
 * @Description: 收支分类，途径
 */
@Service
public class WaysServiceImpl implements WaysService {

    @Autowired
    private IncomeTypeMapper incomeTypeMapper;

    @Autowired
    private ExpenditureTypeMapper expenditureTypeMapper;

    @Override
    public ExpenditureType addExpenditureType(IncomeAndExpenditureTypeParam param) {
        ExpenditureType newExpenditure = new ExpenditureType();
        String userId = UserUtils.getCurrentUserId();
        Long pid = param.getPid();
        int count;
        if(pid != null) {
            ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(pid);
            if(expenditureType == null) {
                // todo throw error
            }
            newExpenditure.setPid(pid);
            count = expenditureTypeMapper.selectCount(newExpenditure);
        } else {
            newExpenditure.setCreatorId(userId);
            count = expenditureTypeMapper.selectCount(newExpenditure);
        }
        BeanUtils.copyProperties(param, newExpenditure);
        newExpenditure.setOrderId(count+1);
        newExpenditure.setCreatorId(userId);
        expenditureTypeMapper.insertSelective(newExpenditure);
        return newExpenditure;
    }

    @Override
    public ExpenditureType updateExpenditureType(IncomeAndExpenditureTypeParam param) {
        Long id = param.getId();
        ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(id);
        if(expenditureType == null) {
            // todo throw error
        }
        BeanUtils.copyProperties(param, expenditureType);
        expenditureTypeMapper.updateByPrimaryKey(expenditureType);
        return expenditureType;
    }

    @Override
    public void removeExpenditureType() {

    }

    @Override
    public void orderExpenditureType() {

    }

    @Override
    public void addIncomeType() {

    }

    @Override
    public void removeIncomeType() {

    }

    @Override
    public void orderIncomeType() {

    }
}
