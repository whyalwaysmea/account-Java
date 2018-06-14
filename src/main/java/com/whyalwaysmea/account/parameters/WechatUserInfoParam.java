package com.whyalwaysmea.account.parameters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Long
 * @Date: Create in 17:27 2018/4/11
 * @Description:
 */
@ApiModel("用户信息")
@Getter
@Setter
public class WechatUserInfoParam {

    private String openId;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户头像, 若用户更换头像，原有头像URL将失效。")
    private String avatarUrl;

    @ApiModelProperty("用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private Integer gender;

    @ApiModelProperty("用户所在城市")
    private String city;

    @ApiModelProperty("用户所在省份")
    private String province;

    @ApiModelProperty("用户所在国家")
    private String country;

    @ApiModelProperty("是否创建默认账本")
    private boolean createBook;

}
