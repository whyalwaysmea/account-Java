package com.whyalwaysmea.account.controller.common;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author: Long
 * @Date: Create in 14:59 2018/4/19
 * @Description:
 */
public class BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
