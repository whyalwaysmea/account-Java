package com.whyalwaysmea.account.parameters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/15 20:24
 * @Description:
 */
@ApiModel("收支记录")
@Data
public class RecordParam {

    @ApiModelProperty("记录id")
    private Integer id;

    @ApiModelProperty("金额(分)")
    private Integer amount;

    @ApiModelProperty("收入(1)or支出(2)")
    private Byte recordType;

    @ApiModelProperty("主要分类")
    private Long mainType;

    @ApiModelProperty("次要分类")
    private Long secondaryType;

    @ApiModelProperty("收支途径")
    private Long payIncomeWay;

    @ApiModelProperty("记录关联的日期 yyyy-MM-dd ")
    private Date recordTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("消费者的id")
    private List<String> partersId;
}
