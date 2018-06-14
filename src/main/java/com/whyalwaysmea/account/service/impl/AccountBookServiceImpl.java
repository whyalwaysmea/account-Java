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
import com.whyalwaysmea.account.parameters.RecordParam;
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

import java.util.Date;
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

        return accountBookMapper.selectByPrimaryKey(accountBook.getId());
    }

    @Override
    public boolean delAccountBook(long id) {
        AccountBook accountBook = accountBookMapper.selectByPrimaryKey(id);
        List<String> parterIds = accountBook.getParticipants().stream().map(WechatUser::getWechatOpenid).collect(Collectors.toList());
        if(parterIds.contains(getCurrentUserId())) {
            AccountBookParters accountBookParters = new AccountBookParters();
            accountBookParters.setBookId(id);
            accountBookParters.setWechatOpenid(getCurrentUserId());
            int i = partersMapper.delete(accountBookParters);
            return i == 1;
        } else {
            throw new MyException(CommonError.INSUFFICIENT_PERMISSIONS);
        }
    }

    @Override
    @Transactional
    public AccountBook updateAccountBook(AccountBookParam accountBookParam) {
        AccountBook accountBook = getAccountBook(accountBookParam.getId());
        // 账本正确性
        List<String> parterIds = accountBook.getParticipants().stream().map(WechatUser::getWechatOpenid).collect(Collectors.toList());
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

        // 同步账本参与人
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

            if(!accountBookParam.getParticipantIds().contains(getCurrentUserId())) {
                AccountBookParters accountBookParters = new AccountBookParters();
                accountBookParters.setBookId(accountBookParam.getId());
                accountBookParters.setWechatOpenid(getCurrentUserId());
                newParters.add(accountBookParters);
            }

            if(newParters.size() > 1) {
                accountBook.setMultipleType(true);
            } else {
                accountBook.setMultipleType(false);
            }

            partersMapper.insertList(newParters);
        }

        accountBookMapper.updateByPrimaryKeySelective(accountBook);

        return accountBookMapper.selectByPrimaryKey(accountBook.getId());
    }

    @Override
    public boolean updateAccountRecord(RecordParam recordParam) {
        AccountBook accountBook = accountBookMapper.selectByPrimaryKey(recordParam.getBookId());
        if(accountBook == null) {
            throw new MyException(AccountBookError.ERROR_ACCOUNTBOOK);
        }
        // 最后记账时间
        accountBook.setLastAccountTime(new Date());
        // 预算
        Integer budgetaryAmount = accountBook.getBudgetaryAmount();
        if(budgetaryAmount != null) {
            Integer costAmount = recordParam.getAmount();
            Integer surplusBudgetaryAmount = accountBook.getSurplusBudgetaryAmount();
            surplusBudgetaryAmount = surplusBudgetaryAmount - costAmount;
            accountBook.setSurplusBudgetaryAmount(surplusBudgetaryAmount);
        }

        accountBookMapper.updateByPrimaryKeySelective(accountBook);
        return true;
    }

    @Override
    public AccountBook getAccountBook(long id) {
        return accountBookMapper.getAccountBook(id);
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


}
