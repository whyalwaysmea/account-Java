package com.whyalwaysmea.account.vo;

import com.whyalwaysmea.account.po.WechatUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 15:40 2018/6/15
 * @Description:
 */
@ApiModel("账本详情")
@Data
public class AccountBookDetails implements Serializable{

    private static final long serialVersionUID = 1376966950553065030L;

    @ApiModelProperty("账本id")
    private Long id;

    @ApiModelProperty("账本名称")
    private String name;

    @ApiModelProperty("账本封面url")
    private String coverImg;

    @ApiModelProperty("账本拥有者id")
    private String ownerId;

    @ApiModelProperty("账本创建者")
    private String creatorId;

    @ApiModelProperty("是否是默认账本")
    private Boolean defaultBook;

    @ApiModelProperty("预算金额（分）")
    private Integer budgetaryAmount;

    @ApiModelProperty("剩余预算金额（分）")
    private Integer surplusBudgetaryAmount;

    @ApiModelProperty("账本类型")
    private Boolean multipleType;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("最后记账时间")
    private Date lastAccountTime;

    @ApiModelProperty("支出金额（分）")
    private Integer expenditureAmount;

    @ApiModelProperty("收入金额（分）")
    private Integer incomeAmount;

    private List<WechatUser> participants = new ArrayList<>();
}
