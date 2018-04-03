package com.whyalwaysmea.account.po;

import javax.persistence.*;

@Table(name = "periodic_account_parter")
public class PeriodicAccountParter {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 周期帐id
     */
    @Column(name = "periodic_account_id")
    private Long periodicAccountId;

    /**
     * 微信openid
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取周期帐id
     *
     * @return periodic_account_id - 周期帐id
     */
    public Long getPeriodicAccountId() {
        return periodicAccountId;
    }

    /**
     * 设置周期帐id
     *
     * @param periodicAccountId 周期帐id
     */
    public void setPeriodicAccountId(Long periodicAccountId) {
        this.periodicAccountId = periodicAccountId;
    }

    /**
     * 获取微信openid
     *
     * @return user_id - 微信openid
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置微信openid
     *
     * @param userId 微信openid
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}