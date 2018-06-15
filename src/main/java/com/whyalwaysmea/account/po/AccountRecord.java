package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "account_record")
@Data
public class AccountRecord implements Serializable {

    private static final long serialVersionUID = 5235296041039815360L;

    /**
     * 账本记录id
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
    private Integer recordType;

    /**
     * 主要分类
     */
    @Column(name = "main_type")
    private String mainType;

    /**
     * 次要分类
     */
    @Column(name = "secondary_type")
    private String secondaryType;

    /**
     * 收支途径
     */
    @Column(name = "pay_income_way")
    private String payIncomeWay;

    /**
     * 创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 记录关联日期
     */
    @Column(name = "record_time")
    private Date recordTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;


}