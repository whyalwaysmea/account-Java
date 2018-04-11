package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.parameters.WaysTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;

/**
 * @Author: Long
 * @Date: Create in 16:39 2018/4/10
 * @Description:    收入、支出分类
 */
public interface WaysService {

    /**
     * 获取用户所有的父支出分类
     */
    PageBean<ExpenditureType> getAllParentExpenditure();

    /**
     * 添加支出分类
     */
    ExpenditureType addExpenditureType(WaysTypeParam param);

    /**
     * 更新支出分类
     * @param param
     */
    ExpenditureType updateExpenditureType(WaysTypeParam param);

    /**
     * 移除支出分类
     */
    boolean deleteExpenditureType(int id);

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
