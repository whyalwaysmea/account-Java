package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.parameters.ExpenditureTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;

import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 16:39 2018/4/10
 * @Description:    收入、支出分类
 */
public interface ExpenditureService {

    /**
     * 获取默认的支出分类
     * @return
     */
    List<ExpenditureType> getAllDefaultExpenditure();

    /**
     * 获取用户所有的父支出分类
     */
    List<ExpenditureType> getAllParentExpenditure();

    /**
     * 获取指定父父类下的所有子条目
     * @param pid
     * @return
     */
    List<ExpenditureType> getChildExpenditureTypeByParendId(int pid);

    /**
     * 添加支出分类
     */
    ExpenditureType addExpenditureType(ExpenditureTypeParam param);

    /**
     * 更新支出分类
     * @param param
     */
    ExpenditureType updateExpenditureType(ExpenditureTypeParam param);

    /**
     * 移除支出分类
     */
    boolean deleteExpenditureType(int id);

    /**
     * 排序支出分类
     */
    void orderExpenditureType();


}
