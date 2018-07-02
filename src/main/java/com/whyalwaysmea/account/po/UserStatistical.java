package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Long
 * @Date: Create in 16:33 2018/7/2
 * @Description:
 */
@Table(name = "user_statistical_data")
@Data
public class UserStatistical implements Serializable {
    private static final long serialVersionUID = 5082313655174532766L;

    @Id
    @Column(name = "wechat_openid")
    private String wechatOpenid;

    @Column(name = "last_account_time")
    private Date lastAccountTime;

    @Column(name = "total_record_times")
    private Integer totalRecordTimes;

    @Column(name = "continuity_account_days")
    private Integer continuityAccountDays;
}
