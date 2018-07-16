package com.whyalwaysmea.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Long
 * @Date: Create in 14:55 2018/7/10
 * @Description:
 */
@ApiModel("所有账本")
@Data
public class BookListVO {

    @ApiModelProperty("账本id")
    private long bookId;

    @ApiModelProperty("账本名称")
    private String name;

    @ApiModelProperty("成员人数")
    private int memberNums;

    @ApiModelProperty("记录总笔数")
    private int recordNums;
}
