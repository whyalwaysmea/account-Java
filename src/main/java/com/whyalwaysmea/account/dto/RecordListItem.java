package com.whyalwaysmea.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whyalwaysmea.account.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 15:32 2018/7/3
 * @Description:
 */
@ApiModel("记录列表")
@Data
public class RecordListItem implements Serializable {

    private static final long serialVersionUID = -2732628719700438464L;

    @ApiModelProperty("记录id")
    private Long id;

    @ApiModelProperty("账本id")
    private Long bookId;

    @ApiModelProperty("金额（分）")
    private Long amount;

    @ApiModelProperty("收支分类  1-收入 2-支出")
    private Integer recordType;

    @ApiModelProperty("主要分类")
    private String mainType;

    @ApiModelProperty("次要分类")
    private String secondaryType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("记录关联时间")
    @JsonFormat(pattern = DateUtils.DATE_FORMAT_PATTERN, timezone = "GMT+8")
    private Date recordTime;

    @ApiModelProperty("关联者头像")
    private List<String> avatorUrls;

    @ApiModelProperty("记录者的头像")
    private String recordAvatorUrl;
}
