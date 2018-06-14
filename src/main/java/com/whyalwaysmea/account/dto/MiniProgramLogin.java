package com.whyalwaysmea.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: Long
 * @Date: Create in 17:09 2018/6/12
 * @Description:
 */
@Getter
@Setter
public class MiniProgramLogin implements Serializable{

    private String openid;

    private String session_key;

    private String errcode;

    private String errmsg;
}
