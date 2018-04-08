package com.whyalwaysmea.account.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/8 21:03
 * @Description:
 */
public class UserUtils {

    /**
     * 获取当前登录的用户id
     * @return
     */
    public static String getCurrentUserId() {
        Subject subject = SecurityUtils.getSubject();
        if(subject == null) {
            return null;
        }
        String userId = (String) subject.getPrincipal();
        return userId;
    }
}
