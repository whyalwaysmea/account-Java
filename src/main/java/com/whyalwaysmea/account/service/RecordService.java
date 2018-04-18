package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountRecord;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/18 20:47
 * @Description:
 */
public interface RecordService {

    /**
     * 新增记录
     * @param recordParam
     * @return
     */
    AccountRecord addRecord(RecordParam recordParam);


    /**
     * 更新记录
     * @param recordParam
     * @return
     */
    AccountRecord updateRecord(RecordParam recordParam);

    /**
     * 删除记录
     * @param id
     * @return
     */
    boolean delRecord(long id);
}
