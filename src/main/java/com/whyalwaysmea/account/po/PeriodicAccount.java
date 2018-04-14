package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "periodic_account")
@Data
public class PeriodicAccount {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账本id
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 金额
     */
    private Integer amount;

    /**
     * 收入(1)or支出(2)
     */
    @Column(name = "record_type")
    private Byte recordType;

    /**
     * 主要分类
     */
    @Column(name = "main_type")
    private Long mainType;

    /**
     * 次要分类
     */
    @Column(name = "secondary_type")
    private Long secondaryType;

    /**
     * 收支途径
     */
    @Column(name = "pay_income_way")
    private Long payIncomeWay;

    /**
     * 周期时间 0：每天 1：每周 2：每月 3：每季度
     */
    @Column(name = "peridoic_type")
    private Byte peridoicType;

    /**
     * 周期点，具体几号或者星期几
     */
    @Column(name = "peridoic_date")
    private Byte peridoicDate;

    /**
     * 创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 自动记账时间
     */
    @Column(name = "record_time")
    private Date recordTime;

    /**
     * 下次记账时间
     */
    @Column(name = "next_record_time")
    private Date nextRecordTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

}