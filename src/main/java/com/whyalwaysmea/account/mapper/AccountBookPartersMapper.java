package com.whyalwaysmea.account.mapper;

import com.whyalwaysmea.account.po.AccountBookParters;
import com.whyalwaysmea.account.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountBookPartersMapper extends MyMapper<AccountBookParters> {

    /**
     * 账本参与者
     * @param id
     * @return
     */
    List<AccountBookParters> getParters(long id);
}