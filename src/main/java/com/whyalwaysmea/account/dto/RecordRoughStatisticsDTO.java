package com.whyalwaysmea.account.dto;

import lombok.Data;

/**
 * @Author: Long
 * @Date: Create in 10:57 2018/7/10
 * @Description:    消费记录统计
 */
@Data
public class RecordRoughStatisticsDTO {

    /**
     * 统计的笔数
     */
    private int totalNum;

    /**
     * 统计的金额
     */
    private int totalMoney;
}
