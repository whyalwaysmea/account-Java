package com.whyalwaysmea.account.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wechat_user")
public class WechatUser {
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
    private Byte gender;

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
     * 获取微信openid
     *
     * @return wechat_openid - 微信openid
     */
    public String getWechatOpenid() {
        return wechatOpenid;
    }

    /**
     * 设置微信openid
     *
     * @param wechatOpenid 微信openid
     */
    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户头像
     *
     * @return avatar_url - 用户头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置用户头像
     *
     * @param avatarUrl 用户头像
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取用户性别,值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @return gender - 用户性别,值为1时是男性，值为2时是女性，值为0时是未知
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置用户性别,值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @param gender 用户性别,值为1时是男性，值为2时是女性，值为0时是未知
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 获取用户所在城市
     *
     * @return city - 用户所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置用户所在城市
     *
     * @param city 用户所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取用户所在省份
     *
     * @return province - 用户所在省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置用户所在省份
     *
     * @param province 用户所在省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取用户所在国家
     *
     * @return country - 用户所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置用户所在国家
     *
     * @param country 用户所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取连续记账天数
     *
     * @return continuity_account_days - 连续记账天数
     */
    public Integer getContinuityAccountDays() {
        return continuityAccountDays;
    }

    /**
     * 设置连续记账天数
     *
     * @param continuityAccountDays 连续记账天数
     */
    public void setContinuityAccountDays(Integer continuityAccountDays) {
        this.continuityAccountDays = continuityAccountDays;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取最后记账时间
     *
     * @return last_account_time - 最后记账时间
     */
    public Date getLastAccountTime() {
        return lastAccountTime;
    }

    /**
     * 设置最后记账时间
     *
     * @param lastAccountTime 最后记账时间
     */
    public void setLastAccountTime(Date lastAccountTime) {
        this.lastAccountTime = lastAccountTime;
    }
}