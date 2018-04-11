package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.WechatUserMapper;
import com.whyalwaysmea.account.parameters.WechatUserInfoParam;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.UserService;
import com.whyalwaysmea.account.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:22
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WechatUserMapper userMapper;

    @Override
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
    public WechatUser login(WechatUserInfoParam param) {
        WechatUser wechatUser = userMapper.selectByPrimaryKey(param.getOpenId());
        // 如果用户存在，则判断是否需要更新资料  头像/昵称
        if(wechatUser != null) {
            BeanUtils.copyProperties(param, wechatUser);
            wechatUser.setWechatOpenid(param.getOpenId());
            wechatUser.setLastLoginTime(new Date());
            userMapper.updateByPrimaryKey(wechatUser);
            return wechatUser;
        }
        // 如果用户不存在，则新增
        wechatUser = new WechatUser();
        BeanUtils.copyProperties(param, wechatUser);
        wechatUser.setWechatOpenid(param.getOpenId());
        userMapper.insertSelective(wechatUser);
        return wechatUser;
    }


}
