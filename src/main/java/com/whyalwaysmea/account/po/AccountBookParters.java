package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "account_book_parters")
@Data
public class AccountBookParters {
    /**
     * 账本id
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 最后记账时间
     */
    @Column(name = "last_account_time")
    private Date lastAccountTime;

    /**
     * 微信用户openid
     */
    @Column(name = "wechat_openid")
    private String wechatOpenid;
}