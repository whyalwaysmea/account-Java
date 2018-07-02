package com.whyalwaysmea.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whyalwaysmea.account.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Long
 * @Date: Create in 16:25 2018/7/2
 * @Description:
 */
@ApiModel("账本参与者")
@Data
public class AccountBookPartersVO implements Serializable{

    private static final long serialVersionUID = -3900675831275945568L;

    /**
     * 账本id
     */
    @ApiModelProperty("账本id")
    private Long bookId;

    /**
     * 最后记账时间
     */
    @ApiModelProperty("最后记账时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_FORMAT_PATTERN, timezone = "GMT+8")
    private Date lastAccountTime;

    /**
     * 微信用户openid
     */
    @ApiModelProperty("微信用户openid")
    private String wechatOpenid;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户头像url")
    private String avatarUrl;
}
