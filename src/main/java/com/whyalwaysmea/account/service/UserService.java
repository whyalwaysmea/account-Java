package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.parameters.WechatUserInfoParam;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.vo.UserStatisticalVO;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:21
 * @Description:
 */
public interface UserService {

    /**
     * 获取微信用户信息
     * @param openid    微信openid
     * @return
     */
    WechatUser getWechatUser(String openid);

    /**
     * 根据token获取当前用户id
     * @return
     */
    String getCurrentUserId();

    /**
     * 根据token获取当前用户
     */
    WechatUser getCurrentUser();

    /**
     * 根据openid登录
     * @param openId
     * @return
     */
    WechatUser login(String openId);

    /**
     * 更新最后登录时间
     */
    WechatUser updateLastActivityDate();

    /**
     * 初始化收支相关基础信息
     */
    void initAccountInfo(String openId);

    /**
     * 更新用户信息
     * @param infoParam
     * @return
     */
    WechatUser updateInfo(WechatUserInfoParam infoParam);

    /**
     * 更新用户最后记账时间
     * @param userId
     */
    void updateLastAccountTime(String userId);

    /**
     * 获取用户统计数据
     * @return
     */
    UserStatisticalVO getUserStatistics();
}
