package com.whyalwaysmea.account.utils;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: Long
 * @Date: Create in 14:25 2018/4/3
 * @Description: 继承自己的MyMapper
 */
public interface MyMapper<T> extends Mapper<T>, InsertListMapper<T> {
}
