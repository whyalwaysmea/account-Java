package com.whyalwaysmea.account.mapper;

import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenditureTypeMapper extends MyMapper<ExpenditureType> {

    /**
     * 获取最大的排序数字
     * @param userId
     * @param pid
     * @return
     */
    long getMaxOrderId(@Param("userId") String userId, @Param("pid") Long pid);

    /**
     * 获取所有父子结构的支出分类
     * @param userId
     * @return
     */
    List<ExpenditureType> findAll(@Param("userId") String userId);
}