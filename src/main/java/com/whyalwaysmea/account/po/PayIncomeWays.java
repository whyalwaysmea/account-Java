package com.whyalwaysmea.account.po;

import javax.persistence.*;

@Table(name = "pay_income_ways")
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
     * 收支方式创建者， -1表示所有人都有
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 获取方式id
     *
     * @return id - 方式id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置方式id
     *
     * @param id 方式id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取收支方式名称
     *
     * @return name - 收支方式名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收支方式名称
     *
     * @param name 收支方式名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取收支方式创建者， -1表示所有人都有
     *
     * @return creator_id - 收支方式创建者， -1表示所有人都有
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置收支方式创建者， -1表示所有人都有
     *
     * @param creatorId 收支方式创建者， -1表示所有人都有
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}