package com.whyalwaysmea.account.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "expenditure_type")
@Getter
@Setter
public class ExpenditureType {
    /**
     * 分类id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类父id
     */
    private Long pid;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 分类icon url
     */
    @Column(name = "icon_url")
    private String iconUrl;

    /**
     * 排序id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


}