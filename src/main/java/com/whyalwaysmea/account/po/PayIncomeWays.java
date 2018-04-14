package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "pay_income_ways")
@Data
public class PayIncomeWays {
    /**
     * 方式id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收支方式名称
     */
    private String name;

    /**
     * 收支方式创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * icon的url
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