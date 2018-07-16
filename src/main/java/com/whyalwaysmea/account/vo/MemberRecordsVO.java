package com.whyalwaysmea.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Long
 * @Date: Create in 10:23 2018/7/10
 * @Description:
 */
@ApiModel("成员本月记录")
@Data
public class MemberRecordsVO {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户头像")
    private String userAvatar;

    @ApiModelProperty("当月累计消费金额")
    private int totalExpenditureMoney;

    @ApiModelProperty("当月累计消费笔数")
    private int totalExpenditureNums;

    @ApiModelProperty("当月累计收入金额")
    private int totalIncomeMoney;

    @ApiModelProperty("当月累计收入笔数")
    private int totalIncomeNums;
}
