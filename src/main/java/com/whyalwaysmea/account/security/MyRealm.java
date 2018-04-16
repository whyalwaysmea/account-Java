package com.whyalwaysmea.account.security;

import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Long
 * @Date: Create in 2018/4/6 11:18
 * @Description:
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Resource
    @Lazy
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserToken;
    }

    /**
     * 用户鉴权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 用户验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        WechatUser user = userService.getWechatUser(token);
        if (user == null) {
            throw new AuthenticationException("token invalid");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}
