package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountRecord;
import com.whyalwaysmea.account.vo.MemberRecordsVO;
import com.whyalwaysmea.account.vo.RecordListVO;

import java.util.List;

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

    /**
     * 根据账本id，获取记录
     * @param bookId 账本id
     * @return
     */
    List<RecordListVO> getRecords(long bookId, String date);

    /**
     * 根据账本id，获取成员的当月统计
     * @param bookId
     * @return
     */
    List<MemberRecordsVO> getMemberRecord(long bookId);
}
