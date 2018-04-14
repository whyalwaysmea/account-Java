package com.whyalwaysmea.account.mapper;

import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.utils.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBookMapper extends MyMapper<AccountBook> {

    AccountBook getAccountBook(long id);

}