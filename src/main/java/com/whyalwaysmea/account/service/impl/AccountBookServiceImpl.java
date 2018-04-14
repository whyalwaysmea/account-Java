package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.mapper.AccountBookMapper;
import com.whyalwaysmea.account.mapper.AccountBookPartersMapper;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.parameters.PageParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.po.AccountBookParters;
import com.whyalwaysmea.account.service.AccountBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Long
 * @Date: Create in 16:03 2018/4/13
 * @Description:
 */
@Service
@Slf4j
public class AccountBookServiceImpl extends BaseService implements AccountBookService {

    @Autowired
    private AccountBookMapper accountBookMapper;

    @Autowired
    private AccountBookPartersMapper partersMapper;

    @Override
    @Transactional
    public AccountBook addAccountBook(AccountBookParam accountBookParam) {
        AccountBook accountBook = new AccountBook();
        BeanUtils.copyProperties(accountBookParam, accountBook);
        accountBook.setCreatorId(getCurrentUserId());
        accountBook.setOwnerId(getCurrentUserId());
        Integer budgetaryAmount = accountBookParam.getBudgetaryAmount();
        if(budgetaryAmount != null && budgetaryAmount > 0) {
            accountBook.setSurplusBudgetaryAmount(budgetaryAmount);
        }
        // 添加账本
        accountBookMapper.insertSelective(accountBook);
        // 添加参与者，默认是创建者
        AccountBookParters accountBookParters = new AccountBookParters();
        accountBookParters.setBookId(accountBook.getId());
        accountBookParters.setWechatOpenid(getCurrentUserId());
        partersMapper.insert(accountBookParters);

        return null;
    }

    @Override
    public AccountBook getAccountBook(long id) {
        AccountBook accountBook = accountBookMapper.getAccountBook(id);
        return accountBook;
    }

    @Override
    public PageBean<AccountBook> getAllAccountBook(PageParam pageParam) {
        return null;
    }
}
