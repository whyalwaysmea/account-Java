package com.whyalwaysmea.account.parameters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 17:24 2018/4/13
 * @Description:
 */
@ApiModel("账本")
@Getter
@Setter
public class AccountBookParam {

    @ApiModelProperty("账本id")
    private Long id;

    @ApiModelProperty("账本名称")
    @NotBlank(message = "账本名称不能为空")
    private String name;

    @ApiModelProperty("账本封面url")
    private String coverImg;

    @ApiModelProperty("默认账本")
    private Boolean defaultBook;

    @ApiModelProperty("预算金额")
    private Integer budgetaryAmount;

    @ApiModelProperty("剩余预算金额")
    private Integer surplusBudgetaryAmount;

    @ApiModelProperty("参与者id，创建者的不用传")
    private List<String> participantIds;

}
