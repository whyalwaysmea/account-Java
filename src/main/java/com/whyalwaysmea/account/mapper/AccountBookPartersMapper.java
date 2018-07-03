package com.whyalwaysmea.account.mapper;

import com.whyalwaysmea.account.po.AccountBookParters;
import com.whyalwaysmea.account.utils.MyMapper;
import com.whyalwaysmea.account.vo.AccountBookPartersVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountBookPartersMapper extends MyMapper<AccountBookParters> {

    /**
     * 账本参与者
     * @param bookId 账本id
     * @return
     */
    List<AccountBookPartersVO> getParters(long bookId);

    /**
     * 账本参与者的id
     * @param bookId 账本id
     * @return
     */
    List<String> getParterIds(long bookId);

    /**
     * 更新最后记账时间
     * @param bookId
     * @param userId
     */
    void updateLastAccountTime(@Param("bookId") Long bookId, @Param("userId") String userId);
}