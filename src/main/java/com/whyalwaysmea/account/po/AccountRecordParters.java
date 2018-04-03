package com.whyalwaysmea.account.po;

import javax.persistence.*;

@Table(name = "account_record_parters")
public class AccountRecordParters {
    /**
     * 账本记录id
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
     * 获取账本记录id
     *
     * @return book_id - 账本记录id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 设置账本记录id
     *
     * @param bookId 账本记录id
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