package com.whyalwaysmea.account.controller.common;

import com.whyalwaysmea.account.dto.ExecuteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 16:14
 * @Description:    通过的controller
 */
@RestController
@ApiIgnore
public class CommonController {

    @RequestMapping("/unauthorized")
    public ExecuteResult<String> unauthorized() {
        return ExecuteResult.fail(401, "Unauthorized");
    }
}
