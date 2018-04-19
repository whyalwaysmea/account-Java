package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseControllerTest;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Author: Long
 * @Date: Create in 14:57 2018/4/19
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountBookControllerTest extends BaseControllerTest{


    @Test
    public void addAccountBook() throws Exception {
        AccountBookParam accountBookParam = new AccountBookParam();
        accountBookParam.setName("我的测试账本");
        accountBookParam.setCoverImg("http://dl.bizhi.sogou.com/images/2012/02/11/25025.jpg");

        String content = JsonUtil.obj2String(accountBookParam);

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/accountbook")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .header("Authorization", "hahaha")
                .accept(MediaType.APPLICATION_JSON))                        // 数据的格式
                .andExpect(MockMvcResultMatchers.status().isOk())           // 返回的状态是200
                .andDo(MockMvcResultHandlers.print())                       // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();            //将相应的数据转换为字符串
        logger.info(result);
    }

    @Test
    public void updateAccountBook() throws Exception {
    }

    @Test
    public void getAccountBook() throws Exception {
    }

    @Test
    public void getAllAccountBook() throws Exception {
    }

    @Test
    public void joinAccountBook() throws Exception {
    }

}