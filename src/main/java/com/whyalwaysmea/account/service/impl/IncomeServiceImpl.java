package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.constant.Constant;
import com.whyalwaysmea.account.mapper.IncomeTypeMapper;
import com.whyalwaysmea.account.po.IncomeType;
import com.whyalwaysmea.account.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Long
 * @Date: Create in 14:18 2018/4/19
 * @Description:
 */
@Service
public class IncomeServiceImpl extends BaseService implements IncomeService {

    @Autowired
    private IncomeTypeMapper incomeTypeMapper;

    @Override
    public List<IncomeType> getAllDefaultIncomeType() {
        IncomeType incomeType = new IncomeType();
        incomeType.setCreatorId(Constant.DEFAULT_USER_ID);
        return incomeTypeMapper.select(incomeType);
    }

    @Override
    public void addDefaultIncomeTypeForNewUser(String userId) {
        List<IncomeType> allDefaultIncomeType = getAllDefaultIncomeType();
        List<IncomeType> collect = allDefaultIncomeType.stream().peek(incomeType -> {
                    incomeType.setCreatorId(userId);
                    incomeType.setCreateTime(new Date());
                }).collect(Collectors.toList());
        incomeTypeMapper.insertList(collect);
    }

    @Override
    public List<IncomeType> getAllParentIncomeType() {
        return null;
    }
}
