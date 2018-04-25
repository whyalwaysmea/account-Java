package com.whyalwaysmea.account.service;

/**
 * @Author: Long
 * @Date: Create in 15:38 2018/4/25
 * @Description:
 */
public interface BookParterService {

    /**
     * 参与到账本
     * @return  0表示加入成功  1表示已经加入过的  -1表示失败
     */
    int joinAccountBook(long id);

    /**
     * 创建一个虚拟的成员
     * @return
     */
    Boolean createParter();

    /**
     *
     * @param userId
     * @param bookId
     * @return
     */
    boolean removeParter(String userId, long bookId);
}
