package com.whyalwaysmea.account.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Long
 * @Date: Create in 2018/4/6 11:18
 * @Description:
 */
@Slf4j
public class UserTokenFilter extends BasicHttpAuthenticationFilter {


    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        UserToken token = new UserToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * Header中没有带Authorization，就直接抛错
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                response401(request, response, e.getMessage());
                return false;
            }
            return true;
        } else {
            response401(request, response, null);
            return false;
        }
    }


    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            if(StringUtils.isNotBlank(message)) {
                httpServletResponse.sendRedirect("/api/unauthorized?message=" + message);
            } else {
                httpServletResponse.sendRedirect("/api/unauthorized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
