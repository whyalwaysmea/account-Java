package com.whyalwaysmea.account.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.whyalwaysmea.account.constant.Constant;
import com.whyalwaysmea.account.constant.RedisKey;
import com.whyalwaysmea.account.enums.WaysError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.PayIncomeWaysMapper;
import com.whyalwaysmea.account.po.PayIncomeWays;
import com.whyalwaysmea.account.service.WaysService;
import com.whyalwaysmea.account.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<PayIncomeWays> getAllDefaultWays() {
        String cache = (String) redisTemplate.opsForValue().get(RedisKey.DEFAULT_WAYS);
        if(StringUtils.isBlank(cache)) {
            PayIncomeWays payIncomeWays = new PayIncomeWays();
            payIncomeWays.setCreatorId(Constant.DEFAULT_USER_ID);
            List<PayIncomeWays> waysList = waysMapper.select(payIncomeWays);
            redisTemplate.opsForValue().set(RedisKey.DEFAULT_WAYS, JsonUtil.obj2String(waysList));
            return waysList;
        }
        return JsonUtil.string2Obj(cache, new TypeReference<List<PayIncomeWays>>() {});
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

    @Override
    @Cacheable(key = "#id")
    public PayIncomeWays getPayIncomeWays(long id) {
        PayIncomeWays payIncomeWays = new PayIncomeWays();
        payIncomeWays.setCreatorId(getCurrentUserId());
        payIncomeWays.setId(id);
        payIncomeWays = waysMapper.selectOne(payIncomeWays);
        if(payIncomeWays == null) {
            throw new MyException(WaysError.ERROR_ID);
        }
        return payIncomeWays;
    }

    @Override
    @Cacheable(key = "#userId")
    public List<PayIncomeWays> getUserAllWays(String userId) {
        PayIncomeWays payIncomeWays = new PayIncomeWays();
        payIncomeWays.setCreatorId(userId);
        List<PayIncomeWays> waysList = waysMapper.select(payIncomeWays);
        return waysList;
    }
}
