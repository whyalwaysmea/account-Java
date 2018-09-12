package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.enums.AccountBookError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.AccountBookMapper;
import com.whyalwaysmea.account.mapper.AccountBookPartersMapper;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.po.AccountBookParters;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.BookParterService;
import com.whyalwaysmea.account.vo.AccountBookPartersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Long
 * @Date: Create in 15:44 2018/4/25
 * @Description:
 */
@Service
public class BookParterServiceImpl extends BaseService implements BookParterService {

    @Autowired
    private AccountBookPartersMapper partersMapper;

    @Autowired
    private AccountBookMapper accountBookMapper;

    @Override
    public List<AccountBookPartersVO> joinAccountBook(long id) {
        AccountBook accountBook = accountBookMapper.getAccountBook(id);
        if(accountBook == null) {
            throw new MyException(AccountBookError.ERROR_ACCOUNTBOOK);
        }

        List<AccountBookPartersVO> participants = accountBook.getParticipants();
        List<String> parterIds = participants.stream().map(AccountBookPartersVO::getWechatOpenid).collect(Collectors.toList());

        AccountBookParters accountBookParters = new AccountBookParters();
        accountBookParters.setWechatOpenid(getCurrentUserId());
        accountBookParters.setBookId(id);
        if(!parterIds.contains(getCurrentUserId())) {
            parterIds.add(getCurrentUserId());
            partersMapper.insert(accountBookParters);

            accountBook.setMultipleType(true);
            accountBookMapper.updateByPrimaryKeySelective(accountBook);

            WechatUser currentUser = getCurrentUser();
            AccountBookPartersVO accountBookPartersVO = new AccountBookPartersVO();
            accountBookPartersVO.setAvatarUrl(currentUser.getAvatarUrl());
            participants.add(accountBookPartersVO);
        }

        return participants;
    }

    @Override
    public Boolean createParter() {
        return null;
    }

    @Override
    public boolean removeParter(String userId, long bookId) {
        return false;
    }


    @Override
    public List<AccountBookPartersVO> findAllByBookId(long bookId) {
        List<AccountBookPartersVO> parters = partersMapper.getParters(bookId);
        return parters;
    }
}
