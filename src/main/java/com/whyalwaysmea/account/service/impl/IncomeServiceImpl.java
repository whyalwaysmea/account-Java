package com.whyalwaysmea.account.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.whyalwaysmea.account.constant.Constant;
import com.whyalwaysmea.account.constant.RedisKey;
import com.whyalwaysmea.account.mapper.IncomeTypeMapper;
import com.whyalwaysmea.account.po.IncomeType;
import com.whyalwaysmea.account.service.IncomeService;
import com.whyalwaysmea.account.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<IncomeType> getAllDefaultIncomeType() {
        String cache = (String) redisTemplate.opsForValue().get(RedisKey.DEFAULT_INCOME);
        if(StringUtils.isBlank(cache)) {
            IncomeType incomeType = new IncomeType();
            incomeType.setCreatorId(Constant.DEFAULT_USER_ID);
            List<IncomeType> typeList = incomeTypeMapper.select(incomeType);
            redisTemplate.opsForValue().set(RedisKey.DEFAULT_INCOME, JsonUtil.obj2String(typeList));
            return typeList;
        }
        return JsonUtil.string2Obj(cache, new TypeReference<List<IncomeType>>() {});
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
