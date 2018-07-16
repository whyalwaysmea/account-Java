package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.dto.MiniProgramLogin;
import com.whyalwaysmea.account.parameters.WechatUserInfoParam;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.UserService;
import com.whyalwaysmea.account.utils.JsonUtil;
import com.whyalwaysmea.account.vo.UserStatisticalVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Value("${wechat.loginUrl}")
    private String wechatUrl;

    private RestTemplate restTemplate = new RestTemplate();

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
    @GetMapping("/login")
    public ExecuteResult<WechatUser> login(@RequestParam("code") @ApiParam("临时登录凭证code") String code) {
        String formatUrl = String.format(wechatUrl, code);
        String result = restTemplate.getForObject(formatUrl, String.class);
        MiniProgramLogin miniProgramLogin = JsonUtil.string2Obj(result, MiniProgramLogin.class);
        WechatUser wechatUser = userService.login(miniProgramLogin.getOpenid());

        return ExecuteResult.ok(wechatUser);
    }


    @ApiOperation("更新用户最后活动时间")
    @PostMapping("/updateLastActivityDate")
    public ExecuteResult<WechatUser> updateLastActivityDate() {
        WechatUser wechatUser = userService.updateLastActivityDate();
        return ExecuteResult.ok(wechatUser);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/updateInfo")
    public ExecuteResult<WechatUser> updateUserInfo(@RequestBody WechatUserInfoParam userInfoParam) {
        WechatUser wechatUser = userService.updateInfo(userInfoParam);
        return ExecuteResult.ok(wechatUser);
    }

    @ApiOperation("获取当前用户")
    @GetMapping
    public ExecuteResult<WechatUser> getCurrentUser() {
        WechatUser currentUser = userService.getCurrentUser();
        return ExecuteResult.ok(currentUser);
    }

    @ApiOperation("创建一个虚拟用户，相当于多人账本中的用户")
    @PostMapping("/register/fackerUser")
    public ExecuteResult<WechatUser> regiterFackerUser() {
        return ExecuteResult.ok();
    }

    @ApiOperation("获取用户数据统计")
    @GetMapping("/statistics")
    public ExecuteResult<UserStatisticalVO> getUserStatistics() {
        return ExecuteResult.ok(userService.getUserStatistics());
    }
}


