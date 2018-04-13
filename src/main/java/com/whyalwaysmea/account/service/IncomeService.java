package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.po.IncomeType;

import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 15:48 2018/4/13
 * @Description:    收入
 */
public interface IncomeService {

    /**
     * 获取所有的默认收入分类
     * @return
     */
    List<IncomeType> getAllDefaultIncomeType();

    /**
     * 获取用户所有的收入分类
     * @return
     */
    List<IncomeType> getAllParentIncomeType();

}
