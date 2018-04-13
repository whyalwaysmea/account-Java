package com.whyalwaysmea.account.controller.common;

import com.whyalwaysmea.account.exception.ValidException;
import org.springframework.validation.BindingResult;

/**
 * @Author: Long
 * @Date: Create in 15:44 2018/4/11
 * @Description:
 */
public class BaseController {

    public void checkParam(BindingResult result) {
        if(result.hasErrors()) {
            String errorMessage = result.getFieldError().getDefaultMessage();
            throw new ValidException(errorMessage);
        }
    }

}
