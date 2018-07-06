package com.whyalwaysmea.account.po;

import com.whyalwaysmea.account.vo.AccountBookPartersVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "account_book")
@Data
@ApiModel("账本详情")
public class AccountBook {
    /**
     * 账本id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("账本id")
    private Long id;

    /**
     * 账本名称
     */
    @ApiModelProperty("账本名称")
    private String name;

    /**
     * 账本封面url
     */
    @Column(name = "cover_img")
    @ApiModelProperty("账本封面url")
    private String coverImg;

    /**
     * 账本拥有者
     */
    @Column(name = "owner_id")
    @ApiModelProperty("账本拥有者id")
    private String ownerId;

    /**
     * 账本创建者
     */
    @Column(name = "creator_id")
    @ApiModelProperty("账本创建者")
    private String creatorId;

    /**
     * 是否是默认账本, 1是
     */
    @Column(name = "default_book")
    @ApiModelProperty("是否是默认账本")
    private Boolean defaultBook;

    /**
     * 预算金额（分）
     */
    @Column(name = "budgetary_amount")
    @ApiModelProperty("预算金额（分）")
    private Integer budgetaryAmount;

    @Column(name = "surplus_budgetary_amount")
    @ApiModelProperty("剩余预算金额（分）")
    private Integer surplusBudgetaryAmount;

    /**
     * 剩余预算金额（分）
     */
    @Column(name = "surplus_budgetary_amount")
    @ApiModelProperty("剩余预算金额（分）")
    private Integer e;

    /**
     * 账本类型  0：个人账本 1：多人账本
     */
    @Column(name = "multiple_type")
    @ApiModelProperty("账本类型")
    private Boolean multipleType;

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
    private Boolean isDelete;

    /**
     * 参与者
     */
    @Transient
    private List<AccountBookPartersVO> participants = new ArrayList<>();

}