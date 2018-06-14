package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Table(name = "wechat_user")
@Data
public class WechatUser implements Serializable{

    @Transient
    private static final long serialVersionUID = -4916230468526464823L;

    /**
     * 微信openid
     */
    @Id
    @Column(name = "wechat_openid")
    private String wechatOpenid;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 用户性别,值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer gender;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 连续记账天数
     */
    @Column(name = "continuity_account_days")
    private Integer continuityAccountDays;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 最后记账时间
     */
    @Column(name = "last_account_time")
    private Date lastAccountTime;

    /**
     * 是否是虚假用户
     */
    private Boolean fakerUser;

    /**
     * 默认账本id
     */
    private Long defaultAccount;
}