package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.AccountBookPartersMapper;
import com.whyalwaysmea.account.service.AccountBookParterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Long
 * @Date: Create in 16:50 2018/7/2
 * @Description:
 */
@Service
public class AccountBookParterServiceImpl implements AccountBookParterService {

    @Autowired
    private AccountBookPartersMapper partersMapper;

    @Override
    public void updateParterLastAccountTime(Long bookId, String userId) {
        partersMapper.updateLastAccountTime(bookId, userId);
    }
}
