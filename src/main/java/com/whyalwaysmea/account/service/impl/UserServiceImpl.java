package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.WechatUserMapper;
import com.whyalwaysmea.account.mq.QueueEnum;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.parameters.WechatUserInfoParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.*;
import com.whyalwaysmea.account.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Autowired
    private AccountBookService accountBookService;

    private String defaultBookName = "%s的默认账本";

    @Autowired
    private AmqpTemplate amqpTemplate;

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
        WechatUser user = getWechatUser(currentUserId);
        return user;
    }

    @Override
    public WechatUser login(String openId) {
        WechatUser wechatUser = getWechatUser(openId);
        if(wechatUser != null) {
            wechatUser.setLastLoginTime(new Date());

            userMapper.updateByPrimaryKey(wechatUser);
            return wechatUser;
        }
        // 如果用户不存在，则新增，
        wechatUser = new WechatUser();
        wechatUser.setWechatOpenid(openId);
        userMapper.insertSelective(wechatUser);

        amqpTemplate.convertAndSend(QueueEnum.USER_REGISTER.getName(), openId);

        return wechatUser;
    }


    @Override
    public WechatUser updateLastActivityDate() {
        WechatUser currentUser = getCurrentUser();
        currentUser.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKey(currentUser);
        return currentUser;
    }

    @Override
    @RabbitListener(queues = "user.register")
    @RabbitHandler
    public void initAccountInfo(String openId) {
        WechatUser wechatUser = getWechatUser(openId);
        if(wechatUser != null) {
            expenditureService.addDefaultExpenditureForNewUser(openId);
            waysService.addDefaultWaysForNewUser(openId);
            incomeService.addDefaultIncomeTypeForNewUser(openId);
        }
    }


    @Override
    @CachePut(key = "#result.getWechatOpenid()")
    public WechatUser updateInfo(WechatUserInfoParam infoParam) {
        WechatUser currentUser = getCurrentUser();
        BeanUtils.copyProperties(infoParam, currentUser);
        if(infoParam.isCreateBook()) {
            AccountBookParam accountBookParam = new AccountBookParam();
            accountBookParam.setDefaultBook(true);
            String bookName = String.format(defaultBookName, infoParam.getNickName());
            accountBookParam.setName(bookName);
            accountBookParam.setCoverImg(infoParam.getAvatarUrl());
            AccountBook accountBook = accountBookService.addAccountBook(accountBookParam);
            currentUser.setDefaultAccount(accountBook.getId());
        }
        userMapper.updateByPrimaryKeySelective(currentUser);
        return currentUser;
    }

    @Override
    public void updateLastAccountTime(String userId) {
        WechatUser wechatUser = new WechatUser();
        wechatUser.setWechatOpenid(userId);
        wechatUser.setLastAccountTime(new Date());
        userMapper.updateByPrimaryKeySelective(wechatUser);
    }


}
