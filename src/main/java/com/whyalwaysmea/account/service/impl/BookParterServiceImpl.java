package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.enums.AccountBookError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.AccountBookMapper;
import com.whyalwaysmea.account.mapper.AccountBookPartersMapper;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.po.AccountBookParters;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.BookParterService;
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
    public int joinAccountBook(long id) {
        AccountBook accountBook = accountBookMapper.getAccountBook(id);
        if(accountBook == null) {
            throw new MyException(AccountBookError.ERROR_ACCOUNTBOOK);
        }

        List<WechatUser> participants = accountBook.getParticipants();
        List<String> parterIds = participants.stream().map(WechatUser::getWechatOpenid).collect(Collectors.toList());

        AccountBookParters accountBookParters = new AccountBookParters();
        accountBookParters.setWechatOpenid(getCurrentUserId());
        accountBookParters.setBookId(id);
        if(!parterIds.contains(getCurrentUserId())) {
            parterIds.add(getCurrentUserId());
            partersMapper.insert(accountBookParters);

            accountBook.setMultipleType(true);
            accountBookMapper.updateByPrimaryKeySelective(accountBook);
            return 0;
        }

        // 如果已经在账本中
        AccountBookParters parter = partersMapper.selectOne(accountBookParters);
        if(parter != null) {
            return 1;
        }

        return -1;
    }

    @Override
    public Boolean createParter() {
        return null;
    }
}
