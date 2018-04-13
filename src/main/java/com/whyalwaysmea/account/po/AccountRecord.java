package com.whyalwaysmea.account.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "account_record")
@Setter
@Getter
public class AccountRecord {
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

    /**
     * 最后记账时间
     */
    @Column(name = "last_account_time")
    private Date lastAccountTime;

    /**
     * 备注
     */
    private String remark;
}