package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.WechatUserMapper;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void saveWechatUser(WechatUser wechatUser) {

    }


}
