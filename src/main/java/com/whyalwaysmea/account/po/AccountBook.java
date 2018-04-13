package com.whyalwaysmea.account.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "account_book")
@Getter
@Setter
public class AccountBook {
    /**
     * 账本id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账本名称
     */
    private String name;

    /**
     * 账本封面url
     */
    @Column(name = "cover_img")
    private String coverImg;

    /**
     * 账本拥有者
     */
    @Column(name = "owner_id")
    private String ownerId;

    /**
     * 账本创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 是否是默认账本
     */
    @Column(name = "default_book")
    private String defaultBook;

    /**
     * 预算金额（分）
     */
    @Column(name = "budgetary_amount")
    private Integer budgetaryAmount;

    /**
     * 剩余预算金额（分）
     */
    @Column(name = "surplus_budgetary_amount")
    private Integer surplusBudgetaryAmount;

    /**
     * 账本类型  1：个人账本 2：多人账本
     */
    @Column(name = "book_type")
    private Byte bookType;

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
     * 是否被删除， 1被删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    /**
     * 参与者
     */
    private List<WechatUser> participant;
}