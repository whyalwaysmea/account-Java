package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.WechatUserInfoParam;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:13
 * @Description:    用户相关
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户相关")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息")
    @GetMapping("/{openid}")
    public ExecuteResult<WechatUser> getUserInfo(@PathVariable("openid") @ApiParam("微信openid") String openid) {
        WechatUser wechatUser = userService.getWechatUser(openid);
        if(wechatUser == null) {
            return ExecuteResult.fail();
        }
        return ExecuteResult.ok(wechatUser);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ExecuteResult<WechatUser> login(@RequestBody WechatUserInfoParam wechatUserInfo) {
        WechatUser wechatUser = userService.login(wechatUserInfo);
        return ExecuteResult.ok(wechatUser);
    }

    @ApiOperation("获取当前用户")
    @GetMapping
    public ExecuteResult<WechatUser> getCurrentUser() {
        WechatUser currentUser = userService.getCurrentUser();
        return ExecuteResult.ok(currentUser);
    }

}


