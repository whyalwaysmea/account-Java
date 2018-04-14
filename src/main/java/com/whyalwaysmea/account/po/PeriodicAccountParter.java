package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.*;

@Table(name = "periodic_account_parter")
@Data
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

}