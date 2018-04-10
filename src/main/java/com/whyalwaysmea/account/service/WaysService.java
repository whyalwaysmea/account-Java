package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.parameters.IncomeAndExpenditureTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;

/**
 * @Author: Long
 * @Date: Create in 16:39 2018/4/10
 * @Description:    收入、支出分类
 */
public interface WaysService {

    /**
     * 添加支出分类
     */
    ExpenditureType addExpenditureType(IncomeAndExpenditureTypeParam param);

    /**
     * 更新支出分类
     * @param param
     */
    ExpenditureType updateExpenditureType(IncomeAndExpenditureTypeParam param);

    /**
     * 移除支出分类
     */
    void removeExpenditureType();

    /**
     * 排序支出分类
     */
    void orderExpenditureType();

    /**
     * 添加收入分类
     */
    void addIncomeType();

    /**
     * 移除收入分类
     */
    void removeIncomeType();

    /**
     * 排序收入分类
     */
    void orderIncomeType();
}
