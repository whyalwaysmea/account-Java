package com.whyalwaysmea.account.mapper;

import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenditureTypeMapper extends MyMapper<ExpenditureType> {

    /**
     * 获取最大的排序数字
     * @param userId
     * @param pid
     * @return
     */
    long getMaxOrderId(@Param("userId") String userId, @Param("pid") Long pid);
}