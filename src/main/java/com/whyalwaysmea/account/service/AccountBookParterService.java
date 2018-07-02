package com.whyalwaysmea.account.service;

/**
 * @Author: Long
 * @Date: Create in 16:49 2018/7/2
 * @Description:
 */
public interface AccountBookParterService {

    /**
     * 更新账本参与者的最后记账时间
     * @param bookId
     * @param userId
     */
    void updateParterLastAccountTime(Long bookId, String userId);
}
