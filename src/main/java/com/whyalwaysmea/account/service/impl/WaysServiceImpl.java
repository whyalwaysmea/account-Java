package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.constant.Constant;
import com.whyalwaysmea.account.mapper.PayIncomeWaysMapper;
import com.whyalwaysmea.account.po.PayIncomeWays;
import com.whyalwaysmea.account.service.WaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Long
 * @Date: Create in 14:19 2018/4/19
 * @Description:
 */
@Service
@CacheConfig(cacheNames = "ways")
public class WaysServiceImpl extends BaseService implements WaysService {

    @Autowired
    private PayIncomeWaysMapper waysMapper;

    @Override
    @Cacheable(key = "'defaultAll'")
    public List<PayIncomeWays> getAllDefaultWays() {
        PayIncomeWays payIncomeWays = new PayIncomeWays();
        payIncomeWays.setCreatorId(Constant.DEFAULT_USER_ID);
        return waysMapper.select(payIncomeWays);
    }

    @Override
    public void addDefaultWaysForNewUser(String userId) {
        List<PayIncomeWays> allDefaultWays = getAllDefaultWays();
        List<PayIncomeWays> collect = allDefaultWays.stream().peek(payIncomeWays -> {
            payIncomeWays.setCreatorId(userId);
            payIncomeWays.setCreateTime(new Date());
        }).collect(Collectors.toList());
        waysMapper.insertList(collect);
    }
}
