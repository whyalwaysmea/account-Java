package com.whyalwaysmea.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whyalwaysmea.account.dto.RecordListItem;
import com.whyalwaysmea.account.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 16:32 2018/7/3
 * @Description:
 */
@ApiModel("记录列表")
@Data
public class RecordListVO implements Serializable {

    private static final long serialVersionUID = -2732628719700438464L;

    @ApiModelProperty("记录详情")
    private List<RecordListItem> items;

    @ApiModelProperty("关联日期")
    @JsonFormat(pattern = DateUtils.DATE_FORMAT_PATTERN, timezone = "GMT+8")
    private Date date;

    @ApiModelProperty("收入金额（分）")
    private Long incomeAmount;

    @ApiModelProperty("支出金额（分）")
    private Long expenditureAmount;
}
