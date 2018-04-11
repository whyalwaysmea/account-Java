package com.whyalwaysmea.account.parameters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Long
 * @Date: Create in 16:46 2018/4/10
 * @Description:
 */
@ApiModel("收入分类")
@Getter
@Setter
public class WaysTypeParam {

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
    private int orderId;

}
