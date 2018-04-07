package com.whyalwaysmea.account.controller.common;

import com.whyalwaysmea.account.dto.ExecuteResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ExecuteResult<String> unauthorized(@RequestParam(value = "message", required = false) String message) {
        if(StringUtils.isNotBlank(message)) {
            return ExecuteResult.fail(401, message);
        } else {
            return ExecuteResult.fail(401, "Unauthorized");
        }
    }
}
