package com.whyalwaysmea.account.po;

import javax.persistence.*;

@Table(name = "account_book_parters")
public class AccountBookParters {
    /**
     * 账本id
     */
    @Id
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 微信用户openid
     */
    @Id
    @Column(name = "wechat_openid")
    private String wechatOpenid;

    /**
     * 获取账本id
     *
     * @return book_id - 账本id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 设置账本id
     *
     * @param bookId 账本id
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取微信用户openid
     *
     * @return wechat_openid - 微信用户openid
     */
    public String getWechatOpenid() {
        return wechatOpenid;
    }

    /**
     * 设置微信用户openid
     *
     * @param wechatOpenid 微信用户openid
     */
    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }
}