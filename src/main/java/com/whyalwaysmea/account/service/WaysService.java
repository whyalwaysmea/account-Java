package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.po.PayIncomeWays;

import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 14:19 2018/4/19
 * @Description:
 */
public interface WaysService {

    /**
     * 获取默认的收支途径
     * @return
     */
    List<PayIncomeWays> getAllDefaultWays();

    /**
     * 为用户添加默认的收支方式
     */
    void addDefaultWaysForNewUser(String userId);

    /**
     * 根据id获取收支途径
     * @param id
     * @return
     */
    PayIncomeWays getPayIncomeWays(long id);
}
