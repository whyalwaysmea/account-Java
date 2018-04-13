package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.mapper.AccountBookMapper;
import com.whyalwaysmea.account.parameters.PageParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Long
 * @Date: Create in 16:03 2018/4/13
 * @Description:
 */
@Service
public class AccountBookServiceImpl implements AccountBookService {

    @Autowired
    private AccountBookMapper accountBookMapper;

    @Override
    public AccountBook addAccountBook() {
        return null;
    }

    @Override
    public PageBean<AccountBook> getAllAccountBook(PageParam pageParam) {
        return null;
    }
}
