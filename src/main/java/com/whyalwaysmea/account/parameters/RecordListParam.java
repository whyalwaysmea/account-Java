package com.whyalwaysmea.account.parameters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Long
 * @Date: Create in 15:13 2018/7/3
 * @Description:
 */
@ApiModel("账本关联的记录")
@Getter
@Setter
public class RecordListParam extends PageParam {

    @ApiModelProperty("账本id")
    private Long bookId;
}
