package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.WechatUserMapper;
import com.whyalwaysmea.account.parameters.WechatUserInfoParam;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.ExpenditureService;
import com.whyalwaysmea.account.service.IncomeService;
import com.whyalwaysmea.account.service.UserService;
import com.whyalwaysmea.account.service.WaysService;
import com.whyalwaysmea.account.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:22
 * @Description:
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private WechatUserMapper userMapper;

    @Autowired
    private ExpenditureService expenditureService;

    @Autowired
    private WaysService waysService;

    @Autowired
    private IncomeService incomeService;

    @Override
    @Cacheable(key = "#openid")
    public WechatUser getWechatUser(String openid) {
        WechatUser wechatUser = userMapper.selectByPrimaryKey(openid);
        return wechatUser;
    }

    @Override
    public String getCurrentUserId() {
        return UserUtils.getCurrentUserId();
    }

    @Override
    public WechatUser getCurrentUser() {
        String currentUserId = getCurrentUserId();
        WechatUser user = userMapper.selectByPrimaryKey(currentUserId);
        return user;
    }

    @Override
    @Transactional
    public WechatUser login(WechatUserInfoParam param) {
        WechatUser wechatUser = userMapper.selectByPrimaryKey(param.getOpenId());
        // 如果用户存在，则更新资料
        if(wechatUser != null) {
            BeanUtils.copyProperties(param, wechatUser);
            wechatUser.setWechatOpenid(param.getOpenId());
            wechatUser.setLastLoginTime(new Date());
            userMapper.updateByPrimaryKey(wechatUser);
            return wechatUser;
        }
        // 如果用户不存在，则新增，
        wechatUser = new WechatUser();
        BeanUtils.copyProperties(param, wechatUser);
        wechatUser.setWechatOpenid(param.getOpenId());
        userMapper.insertSelective(wechatUser);

        // 同步收支相关基础信息
        String openId = param.getOpenId();
        expenditureService.addDefaultExpenditureForNewUser(openId);
        waysService.addDefaultWaysForNewUser(openId);
        incomeService.addDefaultIncomeTypeForNewUser(openId);

        return wechatUser;
    }

    @Override
    public void updateLastAccountTime(String userId) {
        WechatUser wechatUser = new WechatUser();
        wechatUser.setWechatOpenid(userId);
        wechatUser.setLastAccountTime(new Date());
        userMapper.updateByPrimaryKeySelective(wechatUser);
    }


}
