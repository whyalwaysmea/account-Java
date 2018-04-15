package com.whyalwaysmea.account.mapper;

import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountBookMapper extends MyMapper<AccountBook> {

    AccountBook getAccountBook(long id);

    List<AccountBook> getAllAccountBook(@Param("ids") List<Long> ids);
}