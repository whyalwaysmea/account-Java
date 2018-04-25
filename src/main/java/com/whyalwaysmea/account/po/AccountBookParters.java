package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.*;

@Table(name = "account_book_parters")
@Data
public class AccountBookParters {
    /**
     * 账本id
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 微信用户openid
     */
    @Column(name = "wechat_openid")
    private String wechatOpenid;

    @Transient
    private WechatUser user;

}