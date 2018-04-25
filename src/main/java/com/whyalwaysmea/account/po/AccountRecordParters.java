package com.whyalwaysmea.account.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author Why
 */
@Table(name = "account_record_parters")
@Getter
@Setter
public class AccountRecordParters implements Serializable {

    @Transient
    private static final long serialVersionUID = 3156857501959947804L;

    /**
     * 账本记录id
     */
    private Long recordId;

    /**
     * 微信用户openid
     */
    private String wechatOpenid;

}