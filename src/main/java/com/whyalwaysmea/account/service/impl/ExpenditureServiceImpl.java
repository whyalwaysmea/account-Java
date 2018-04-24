package com.whyalwaysmea.account.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.whyalwaysmea.account.constant.Constant;
import com.whyalwaysmea.account.constant.RedisKey;
import com.whyalwaysmea.account.enums.WaysError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.mapper.ExpenditureTypeMapper;
import com.whyalwaysmea.account.parameters.ExpenditureTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.service.ExpenditureService;
import com.whyalwaysmea.account.utils.JsonUtil;
import com.whyalwaysmea.account.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Long
 * @Date: Create in 16:49 2018/4/10
 * @Description: 收支分类，途径
 */
@Service
@Slf4j
public class ExpenditureServiceImpl extends BaseService implements ExpenditureService {

    @Autowired
    private ExpenditureTypeMapper expenditureTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ExpenditureType> getAllParentDefaultExpenditure() {
        String cache = (String) redisTemplate.opsForValue().get(RedisKey.DEFAULT_PARENT_EXPENDITURE);
        if(StringUtils.isBlank(cache)) {
            Example example = new Example(ExpenditureType.class);
            example.createCriteria()
                    .andEqualTo("creatorId", Constant.DEFAULT_USER_ID)
                    .andIsNull("pid");
            List<ExpenditureType> expenditureList = expenditureTypeMapper.selectByExample(example);
            redisTemplate.opsForValue().set(RedisKey.DEFAULT_PARENT_EXPENDITURE, JsonUtil.obj2String(expenditureList));
            return expenditureList;
        }
        return JsonUtil.string2Obj(cache, new TypeReference<List<ExpenditureType>>() {});
    }

    @Override
    public List<ExpenditureType> getAllChildDefaultExpenditureByPid(long pid) {
        String cache = (String) redisTemplate.opsForValue().get(RedisKey.DEFAULT_CHILD_EXPENDITURE + pid);
        if(StringUtils.isBlank(cache)) {
            Example example = new Example(ExpenditureType.class);
            example.createCriteria()
                    .andEqualTo("creatorId", Constant.DEFAULT_USER_ID)
                    .andEqualTo("pid", pid);
            List<ExpenditureType> expenditureList = expenditureTypeMapper.selectByExample(example);
            redisTemplate.opsForValue().set(RedisKey.DEFAULT_CHILD_EXPENDITURE + pid, JsonUtil.obj2String(expenditureList));
            return expenditureList;
        }
        return JsonUtil.string2Obj(cache, new TypeReference<List<ExpenditureType>>() {});
    }

    @Override
    public void addDefaultExpenditureForNewUser(String userId) {
        // TODO 第一次会很慢，不知道是不是逻辑有问题
        List<ExpenditureType> allDefaultExpenditure = getAllParentDefaultExpenditure();
        for (ExpenditureType expenditureType : allDefaultExpenditure) {
            // 先保存父类
            List<ExpenditureType> childDefaultExpenditure = getAllChildDefaultExpenditureByPid(expenditureType.getId());
            expenditureType.setId(null);
            expenditureType.setCreatorId(userId);
            expenditureType.setCreateTime(new Date());
            expenditureTypeMapper.insert(expenditureType);
            // 再保存子类
            if(CollectionUtils.isNotEmpty(childDefaultExpenditure)) {
                childDefaultExpenditure = childDefaultExpenditure.stream().peek(childExpenditureType -> {
                    childExpenditureType.setId(null);
                    childExpenditureType.setPid(expenditureType.getId());
                    childExpenditureType.setCreatorId(userId);
                    childExpenditureType.setCreateTime(new Date());
                }).collect(Collectors.toList());
                expenditureTypeMapper.insertList(childDefaultExpenditure);
            }
        }
    }

    @Override
    public List<ExpenditureType> getAllParentExpenditure() {
        String currentUserId = UserUtils.getCurrentUserId();
        Example example = new Example(ExpenditureType.class);
        example.createCriteria()
                .andEqualTo("creatorId", currentUserId)
                .andIsNull("pid");
        example.orderBy("orderId").desc();
        return expenditureTypeMapper.selectByExample(example);
    }

    @Override
    public List<ExpenditureType> getAllExpenditure() {
        List<ExpenditureType> all = expenditureTypeMapper.findAll(getCurrentUserId());
        return all;
    }

    @Override
    public List<ExpenditureType> getChildExpenditureTypeByParendId(int pid) {
        String currentUserId = UserUtils.getCurrentUserId();
        ExpenditureType expenditureType = new ExpenditureType();
        expenditureType.setPid((long) pid);
        expenditureType.setCreatorId(currentUserId);
        return expenditureTypeMapper.select(expenditureType);
    }

    @Override
    public ExpenditureType addExpenditureType(ExpenditureTypeParam param) {
        ExpenditureType newExpenditure = new ExpenditureType();
        String userId = UserUtils.getCurrentUserId();
        Long pid = param.getPid();
        if(pid != null) {
            ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(pid);
            if(expenditureType == null) {
                throw new MyException(WaysError.ERROR_Expenditure_PID);
            }
            newExpenditure.setPid(pid);
        }
        long maxOrderId = expenditureTypeMapper.getMaxOrderId(userId, pid);
        BeanUtils.copyProperties(param, newExpenditure);
        newExpenditure.setOrderId(maxOrderId + 10);
        newExpenditure.setCreatorId(userId);
        expenditureTypeMapper.insertSelective(newExpenditure);

        return newExpenditure;
    }

    @Override
    public ExpenditureType updateExpenditureType(ExpenditureTypeParam param) {
        Long id = param.getId();
        ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(id);
        if(expenditureType == null) {
            throw new MyException(WaysError.ERROR_Expenditure_ID);
        }
        BeanUtils.copyProperties(param, expenditureType);
        expenditureTypeMapper.updateByPrimaryKeySelective(expenditureType);
        return expenditureType;
    }

    @Override
    public boolean deleteExpenditureType(int id) {
        ExpenditureType expenditureType = expenditureTypeMapper.selectByPrimaryKey(id);
        if(expenditureType == null) {
            throw new MyException(WaysError.ERROR_Expenditure_ID);
        }
        int delete = expenditureTypeMapper.deleteByPrimaryKey(id);
        return delete == 1 ;
    }

    @Override
    public void orderExpenditureType() {

    }


}
