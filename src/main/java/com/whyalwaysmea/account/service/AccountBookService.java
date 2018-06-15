package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.parameters.PageParam;
import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.vo.AccountBookDetails;

/**
 * @Author: Long
 * @Date: Create in 15:51 2018/4/13
 * @Description:    账本
 */
public interface AccountBookService {

    /**
     * 创建账本
     * @return
     */
    AccountBook addAccountBook(AccountBookParam accountBookParam);

    /**
     * 删除账本
     * @param id
     * @return
     */
    boolean delAccountBook(long id);

    /**
     * 更新账本
     * @param accountBookParam
     * @return
     */
    AccountBook updateAccountBook(AccountBookParam accountBookParam);

    /**
     * 账本统计更新（最后记账时间，预算，）
     * @param recordParam
     * @return
     */
    boolean updateAccountRecord(RecordParam recordParam);

    /**
     * 根据id获取账本信息
     * @param id
     * @return
     */
    AccountBookDetails getAccountBookDetails(long id);


    /**
     * 分页获取账本列表
     * @return
     */
    PageBean<AccountBook> getAllAccountBook(PageParam pageParam);


}
