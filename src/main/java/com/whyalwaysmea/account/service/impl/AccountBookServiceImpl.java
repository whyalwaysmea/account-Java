package com.whyalwaysmea.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.enums.AccountBookError;
import com.whyalwaysmea.account.enums.CommonError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.AccountBookMapper;
import com.whyalwaysmea.account.mapper.AccountBookPartersMapper;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.parameters.PageParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.po.AccountBookParters;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.AccountBookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(rollbackFor=Exception.class)
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
    public AccountBook updateAccountBook(AccountBookParam accountBookParam) {
        AccountBook accountBook = getAccountBook(accountBookParam.getId());
        List<String> parterIds = accountBook.getParticipants().stream().map(wechatUser -> wechatUser.getWechatOpenid()).collect(Collectors.toList());
        if(accountBook == null || !parterIds.contains(getCurrentUserId())) {
            throw new MyException(CommonError.INSUFFICIENT_PERMISSIONS);
        }

        if(StringUtils.isNotEmpty(accountBookParam.getName())) {
            accountBook.setName(accountBookParam.getName());
        }

        if(accountBookParam.getDefaultBook() != null) {
            accountBook.setDefaultBook(accountBookParam.getDefaultBook());
        }

        if(accountBookParam.getBudgetaryAmount() != null) {
            accountBook.setBudgetaryAmount(accountBookParam.getBudgetaryAmount());
        }

        if(accountBookParam.getSurplusBudgetaryAmount() != null) {
            if(accountBookParam.getSurplusBudgetaryAmount() > accountBook.getBudgetaryAmount()) {
                throw new MyException(AccountBookError.ERROR_BUDGETARYAMOUNT);
            } else {
                accountBook.setSurplusBudgetaryAmount(accountBookParam.getSurplusBudgetaryAmount());
            }
        }

        if(CollectionUtils.isNotEmpty(accountBookParam.getParticipantIds())) {
            AccountBookParters delParters = new AccountBookParters();
            delParters.setBookId(accountBookParam.getId());
            partersMapper.delete(delParters);

            List<AccountBookParters> newParters = accountBookParam.getParticipantIds().stream().map(id -> {
                AccountBookParters accountBookParters = new AccountBookParters();
                accountBookParters.setBookId(accountBookParam.getId());
                accountBookParters.setWechatOpenid(id);
                return accountBookParters;
            }).collect(Collectors.toList());
            partersMapper.insertList(newParters);
        }

        accountBookMapper.updateByPrimaryKeySelective(accountBook);

        return null;
    }

    @Override
    public AccountBook getAccountBook(long id) {
        AccountBook accountBook = accountBookMapper.getAccountBook(id);
        return accountBook;
    }

    @Override
    public PageBean<AccountBook> getAllAccountBook(PageParam pageParam) {
        AccountBookParters accountBookParters = new AccountBookParters();
        accountBookParters.setWechatOpenid(getCurrentUserId());
        List<AccountBookParters> parters = partersMapper.select(accountBookParters);
        List<Long> bookIds = parters.stream().map(parter -> parter.getBookId()).collect(Collectors.toList());
        PageHelper.startPage(pageParam);
        if(CollectionUtils.isEmpty(bookIds)) {
            return PageBean.data(null);
        } else {
            List<AccountBook> allAccountBook = accountBookMapper.getAllAccountBook(bookIds);
            return PageBean.data(allAccountBook);
        }
    }

    @Override
    public Boolean joinAccountBook(long id) {
        AccountBook accountBook = accountBookMapper.getAccountBook(id);
        if(accountBook == null) {
            throw new MyException(AccountBookError.ERROR_ACCOUNTBOOK);
        }

        List<WechatUser> participants = accountBook.getParticipants();
        List<String> parterIds = participants.stream().map(wechatUser -> wechatUser.getWechatOpenid()).collect(Collectors.toList());
        if(!parterIds.contains(getCurrentUserId())) {
            parterIds.add(getCurrentUserId());
            AccountBookParters accountBookParters = new AccountBookParters();
            accountBookParters.setWechatOpenid(getCurrentUserId());
            accountBookParters.setBookId(id);
            partersMapper.insert(accountBookParters);
            return true;
        }

        return false;
    }
}
