package com.whyalwaysmea.account.parameters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/15 19:39
 * @Description:
 */
@ApiModel("收入分类")
@Data
public class IncomeParam {

    @ApiModelProperty("条目id")
    private Long id;

    @ApiModelProperty("条目父id")
    private Long pid;

    @ApiModelProperty("条目名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty("icon url")
    private String iconUrl;

    @ApiModelProperty("排序id")
    private Long orderId;
}
