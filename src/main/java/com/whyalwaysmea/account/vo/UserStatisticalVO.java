package com.whyalwaysmea.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Long
 * @Date: Create in 14:47 2018/7/9
 * @Description:
 */
@ApiModel("用户数据统计")
@Data
public class UserStatisticalVO {

    @ApiModelProperty("累计记账笔数")
    private Integer totalRecordTimes;

    @ApiModelProperty("连续记账天数")
    private Integer continuityAccountDays;

    @ApiModelProperty("累计记账天数")
    private Integer totalRecordDays;
}
