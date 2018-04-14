package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.parameters.PageParam;
import com.whyalwaysmea.account.po.AccountBook;

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

    AccountBook getAccountBook(long id);

    /**
     * 分页获取账本列表
     * @return
     */
    PageBean<AccountBook> getAllAccountBook(PageParam pageParam);
}
