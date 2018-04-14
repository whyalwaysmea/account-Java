package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.WechatUserMapper;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/14 10:20
 * @Description:
 */
public class BaseService {

    @Autowired
    private WechatUserMapper userMapper;

    public String getCurrentUserId() {
        return UserUtils.getCurrentUserId();
    }

    public WechatUser getCurrentUser() {
        String currentUserId = getCurrentUserId();
        WechatUser wechatUser = userMapper.selectByPrimaryKey(currentUserId);
        return wechatUser;
    }
}
