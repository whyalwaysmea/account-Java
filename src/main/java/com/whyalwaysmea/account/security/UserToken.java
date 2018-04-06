package com.whyalwaysmea.account.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: Long
 * @Date: Create in 2018/4/6 11:18
 * @Description:
 */
public class UserToken implements AuthenticationToken {

    // 密钥
    private String token;

    public UserToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
